package com.example.p3practico_cs21004.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.p3practico_cs21004.adapters.InmueblesAdapter
import com.example.p3practico_cs21004.databinding.ActivityInmueblesBinding
import com.example.p3practico_cs21004.network.ApiService
import com.example.p3practico_cs21004.network.Inmueble
import com.example.p3practico_cs21004.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InmueblesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInmueblesBinding
    private val apiService: ApiService = RetrofitClient.apiService
    private lateinit var inmueblesAdapter: InmueblesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInmueblesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddInmueble.setOnClickListener {
            // Redirigir a la actividad AgregarInmuebleActivity al hacer clic en el botón
            startActivity(Intent(this, AgregarInmuebleActivity::class.java))
        }


        binding.btnListInmuebles.setOnClickListener {
            fetchInmuebles()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        inmueblesAdapter = InmueblesAdapter()
        binding.recyclerViewInmuebles.apply {
            layoutManager = LinearLayoutManager(this@InmueblesActivity)
            adapter = inmueblesAdapter
        }
    }

    private fun fetchInmuebles() {
        apiService.getInmuebles().enqueue(object : Callback<List<Inmueble>> {
            override fun onResponse(call: Call<List<Inmueble>>, response: Response<List<Inmueble>>) {
                if (response.isSuccessful) {
                    val inmuebles = response.body() ?: emptyList()
                    inmueblesAdapter.submitList(inmuebles)
                    binding.recyclerViewInmuebles.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this@InmueblesActivity, "Error al obtener inmuebles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Inmueble>>, t: Throwable) {
                Toast.makeText(this@InmueblesActivity, "Fallo al conectarse al servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }
}