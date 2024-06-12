package com.example.p3practico_cs21004.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.p3practico_cs21004.databinding.ActivityAgregarInmuebleBinding
import com.example.p3practico_cs21004.network.ApiService
import com.example.p3practico_cs21004.network.Inmueble
import com.example.p3practico_cs21004.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class AgregarInmuebleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarInmuebleBinding
    private val apiService: ApiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarInmuebleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardarInmueble.setOnClickListener {
            guardarInmueble()
        }
    }

    private fun guardarInmueble() {
        val departamento = binding.edtDepartamento.text.toString()
        val municipio = binding.edtMunicipio.text.toString()
        val residencia = binding.edtResidencia.text.toString()
        val calle = binding.edtCalle.text.toString()
        val poligono = binding.edtPoligono.text.toString()
        val numeroCasa = binding.edtNumeroCasa.text.toString().toIntOrNull() ?: 0
        val idPropietario = binding.edtIdPropietario.text.toString().toIntOrNull() ?: 0

        val inmueble = Inmueble(
            id = 0, // El ID será asignado por el servidor
            departamento = departamento,
            municipio = municipio,
            residencia = residencia,
            calle = calle,
            poligono = poligono,
            numero_casa = numeroCasa,
            id_propietario = idPropietario
        )

        apiService.createInmueble(inmueble).enqueue(object : Callback<Inmueble> {
            override fun onResponse(call: Call<Inmueble>, response: Response<Inmueble>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AgregarInmuebleActivity, "Inmueble agregado exitosamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } else {
                    Toast.makeText(this@AgregarInmuebleActivity, "Error al agregar inmueble", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Inmueble>, t: Throwable) {
                Toast.makeText(this@AgregarInmuebleActivity, "Fallo al conectarse al servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun limpiarCampos() {
        binding.edtDepartamento.text.clear()
        binding.edtMunicipio.text.clear()
        binding.edtResidencia.text.clear()
        binding.edtCalle.text.clear()
        binding.edtPoligono.text.clear()
        binding.edtNumeroCasa.text.clear()
        binding.edtIdPropietario.text.clear()
    }
}