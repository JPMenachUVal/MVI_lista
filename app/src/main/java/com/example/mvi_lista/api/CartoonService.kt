package com.example.mvi_lista.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CartoonService {
    const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}