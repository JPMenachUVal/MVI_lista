package com.example.mvi_lista.view

import com.example.mvi_lista.model.Animal

sealed class MainState {
    //Estados posibles ante la acción del click del usuario
    object Idle: MainState()
    object Loading: MainState()
    data class Animals(val animals: List<Animal>): MainState()
    data class Error(val error: String?):MainState()
}