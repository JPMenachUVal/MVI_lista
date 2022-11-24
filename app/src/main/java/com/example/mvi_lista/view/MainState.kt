package com.example.mvi_lista.view

import com.example.mvi_lista.model.Cartoon

//Pueden existir varios estado en la app
sealed class MainState {
    //Abstracción de todos los estados posibles ante la acción del click del usuario
    object Idle: MainState()
    object Loading: MainState()
    //El estado cuando se obtienen los personajes de manera exitosa
    data class Animals(val cartoons: List<Cartoon>): MainState()
    //Cuando no se obtiene lños personajes de manera exitosa
    data class Error(val error: String?):MainState()
}