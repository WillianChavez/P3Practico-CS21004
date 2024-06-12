package com.example.p3practico_cs21004.network


import retrofit2.http.*
import retrofit2.Call

data class Inmueble(
    val id: Int,
    val departamento: String,
    val municipio: String,
    val residencia: String,
    val calle: String,
    val poligono: String,
    val numero_casa: Int,
    val id_propietario: Int
)

data class Propietario(
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val fecha_nacimiento: String,
    val genero: Char,
    val telefono: String,
    val email: String
)

interface ApiService {
    @GET("inmuebles")
    fun getInmuebles(@Query("id") id: Int? = null): Call<List<Inmueble>>

    @POST("inmuebles")
    fun createInmueble(@Body inmueble: Inmueble): Call<Inmueble>

    @GET("propietarios/{id}")
    fun getPropietario(@Path("id") id: Int): Call<List<Propietario>>
    @POST("propietarios")
    fun createPropietario(@Body propietario: Propietario): Call<Propietario>
}
