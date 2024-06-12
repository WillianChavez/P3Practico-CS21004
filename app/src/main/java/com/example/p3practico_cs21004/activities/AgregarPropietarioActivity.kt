package com.example.p3practico_cs21004.activities


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.p3practico_cs21004.R
import com.example.p3practico_cs21004.databinding.ActivityAgregarPropietarioBinding
import com.example.p3practico_cs21004.network.ApiService
import com.example.p3practico_cs21004.network.Propietario
import com.example.p3practico_cs21004.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class AgregarPropietarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarPropietarioBinding
    private val apiService: ApiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarPropietarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            guardarPropietario()
        }
    }

    private fun guardarPropietario() {
        val nombres = binding.edtNombres.text.toString()
        val apellidos = binding.edtApellidos.text.toString()
        val fechaNacimiento = binding.edtFechaNacimiento.text.toString()
        val genero = binding.edtGenero.text.toString().firstOrNull() ?: ' '
        val telefono = binding.edtTelefono.text.toString()
        val email = binding.edtEmail.text.toString()

        val propietario = Propietario(
            id = 0, // El ID será asignado por el servidor
            nombres = nombres,
            apellidos = apellidos,
            fecha_nacimiento = fechaNacimiento,
            genero = genero,
            telefono = telefono,
            email = email
        )

        apiService.createPropietario(propietario).enqueue(object : retrofit2.Callback<Propietario> {
            override fun onResponse(call: Call<Propietario>, response: Response<Propietario>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AgregarPropietarioActivity, "Propietario agregado exitosamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } else {
                    Toast.makeText(this@AgregarPropietarioActivity, "Error al agregar propietario", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Propietario>, t: Throwable) {
                Toast.makeText(this@AgregarPropietarioActivity, "Fallo al conectarse al servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun limpiarCampos() {
        binding.edtNombres.text.clear()
        binding.edtApellidos.text.clear()
        binding.edtFechaNacimiento.text.clear()
        binding.edtGenero.text.clear()
        binding.edtTelefono.text.clear()
        binding.edtEmail.text.clear()
    }
}