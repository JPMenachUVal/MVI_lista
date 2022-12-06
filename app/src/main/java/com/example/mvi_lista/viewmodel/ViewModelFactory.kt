package com.example.mvi_lista.viewmodel

import android.arch.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.example.mvi_lista.api.CartoonApi
import com.example.mvi_lista.api.CartoonRepo
import java.lang.IllegalArgumentException

//Personalizando Factory de ViewModels
//Para poder crear objetos quer puedan recibir argumentos
//Por defecto la fábrica genera objetos con 0 argumentos

class ViewModelFactory(
    private val api: CartoonApi
): androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {//T -> Clase genérica
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(CartoonRepo(api)) as T
        }
        throw IllegalArgumentException("La clase no es válida como ViewModel")
    }
}