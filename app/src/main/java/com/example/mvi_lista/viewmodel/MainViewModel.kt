package com.example.mvi_lista.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_lista.api.CartoonRepo
import com.example.mvi_lista.view.MainIntent
import com.example.mvi_lista.view.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

//Para funcionar como ViewModel, es necesario heredar esta clase:
class MainViewModel(private val repo: CartoonRepo): ViewModel() {
    //Se trabaja con Intents y states.
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    //MutableStateFlow puede fluir entre los estados creados
    private var _state = MutableStateFlow<MainState>(MainState.Idle)
    val state : StateFlow<MainState>
    get() = _state

    init {
        handleInit()
    }

    private fun handleInit() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{
                when(it){
                    //is MainIntent.FetchCartoons //Objeto que refleja la intención de interacción del usuario
                    //fetchCartoons() = función que va a interactura con la lista en base a los estados
                    is MainIntent.FetchCartoons -> fetchCartoons()
                    else -> fetchCartoons()
                }
            }
        }
    }

    private fun fetchCartoons() {
        viewModelScope.launch {
            //Cambiar de estado Idle a Loading
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Cartoons(repo.getCartoons())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}