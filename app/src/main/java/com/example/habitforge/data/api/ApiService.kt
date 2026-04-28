package com.example.habitforge.data.api

import com.example.habitforge.model.DTOs.ObjetivoDTO
import com.example.habitforge.model.LoginRequest
import com.example.habitforge.model.Meta
import com.example.habitforge.model.Objetivo
import com.example.habitforge.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("usuarios")
    suspend fun getUsuarios(): List<Usuario>

    @POST("usuarios/login")
    suspend fun login(@Body request: LoginRequest): Usuario

    @GET("metas/usuario/{id}")
    suspend fun getMetasPorUsuarios(@Path("id") id: Int): List<Meta>
    @GET("objetivos/usuario/{id}")
    suspend fun getObjetivosPorUsuarios(@Path("id") id: Int): List<Objetivo>
    @POST("objetivos")
    suspend fun postObjetivos(@Body request: ObjetivoDTO)
}