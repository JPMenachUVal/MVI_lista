package com.example.mvi_lista.api

import com.example.mvi_lista.model.Cartoon
import retrofit2.http.GET

//Definir las reglas de comunicación con la API a consumir.
interface CartoonApi {
    //Obtener personajes
    @GET("character")
    //Una función suspendida es para trabajar con corrutinas:
    suspend fun getCartoons(): List<Cartoon>
}