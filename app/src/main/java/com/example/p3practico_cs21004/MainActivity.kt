package com.example.p3practico_cs21004

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.p3practico_cs21004.activities.InmueblesActivity
import com.example.p3practico_cs21004.activities.PropietariosActivity

import com.example.p3practico_cs21004.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPropietarios.setOnClickListener {
            val intent = Intent(this, PropietariosActivity::class.java)
            startActivity(intent)
        }

        binding.btnInmuebles.setOnClickListener {
            val intent = Intent(this, InmueblesActivity::class.java)
            startActivity(intent)
        }
    }
}