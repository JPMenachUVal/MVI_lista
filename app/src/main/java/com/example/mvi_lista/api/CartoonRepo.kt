package com.example.mvi_lista.api

class CartoonRepo(val api: CartoonApi) {
    suspend fun getCartoons() = api.getCartoons()
}