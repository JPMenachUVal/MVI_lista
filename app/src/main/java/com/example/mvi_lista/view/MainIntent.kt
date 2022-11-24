package com.example.mvi_lista.view

//Pueden existir diferentes interacciones del usuario con la app
sealed class MainIntent {
    //Abstracción de las interacciones del usuario.
    object FetchCartoons: MainIntent()
}