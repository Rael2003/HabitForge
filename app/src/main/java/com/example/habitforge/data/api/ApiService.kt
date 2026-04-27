package com.example.habitforge.data.api

import com.example.habitforge.model.Usuario
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("usuarios")
    suspend fun getUsuarios(): List<Usuario>
}