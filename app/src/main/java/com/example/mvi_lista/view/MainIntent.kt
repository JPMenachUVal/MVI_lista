package com.example.mvi_lista.view

sealed class MainIntent {
    object FetchAnimals: MainIntent()
}