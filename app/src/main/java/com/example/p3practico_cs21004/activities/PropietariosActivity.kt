package com.example.p3practico_cs21004.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.p3practico_cs21004.databinding.ActivityPropietariosBinding
import com.example.p3practico_cs21004.network.ApiService
import com.example.p3practico_cs21004.network.Propietario
import com.example.p3practico_cs21004.network.RetrofitClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PropietariosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPropietariosBinding
    private val apiService: ApiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropietariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddPropietario.setOnClickListener {
            startActivity(Intent(this, AgregarPropietarioActivity::class.java))
        }

        binding.btnSearchPropietario.setOnClickListener {
            val id = binding.txtPropietarioId.text.toString().toInt()
            fetchPropietario(id)
        }
    }

    private fun fetchPropietario(id: Int) {
        apiService.getPropietario(id).enqueue(object : Callback<List<Propietario>> {
            override fun onResponse(call: Call<List<Propietario>>, response: Response<List<Propietario>>) {
                if (response.isSuccessful) {
                    val propietarios = response.body()
                    if (!propietarios.isNullOrEmpty()) {
                        mostrarPropietarios(propietarios)
                    } else {
                        Toast.makeText(this@PropietariosActivity, "Propietario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@PropietariosActivity, "Error al obtener propietarios", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Propietario>>, t: Throwable) {
                Toast.makeText(this@PropietariosActivity, "Fallo al conectarse al servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun mostrarPropietarios(propietarios: List<Propietario>) {
        val builder = StringBuilder()
        for (propietario in propietarios) {
            builder.append("ID: ${propietario.id}\n" +
                    "Nombres: ${propietario.nombres}\n" +
                    "Apellidos: ${propietario.apellidos}\n" +
                    "Fecha de nacimiento: ${propietario.fecha_nacimiento}\n" +
                    "Género: ${propietario.genero}\n" +
                    "Teléfono: ${propietario.telefono}\n" +
                    "Email: ${propietario.email}\n\n")
        }
        binding.txtPropietarioDetails.text = builder.toString()
        binding.txtPropietarioDetails.visibility = View.VISIBLE
    }
}