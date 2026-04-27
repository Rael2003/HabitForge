package com.example.habitforge.data.api

import com.example.habitforge.model.LoginRequest
import com.example.habitforge.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("usuarios")
    suspend fun getUsuarios(): List<Usuario>

    @POST("usuarios/login")
    suspend fun login(@Body request: LoginRequest): Usuario
}