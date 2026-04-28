package com.example.habitforge.data.repository

import com.example.habitforge.data.api.ApiClient
import com.example.habitforge.model.DTOs.ObjetivoDTO
import com.example.habitforge.model.LoginRequest
import com.example.habitforge.model.Meta
import com.example.habitforge.model.Objetivo

class ObjetivoRepository {
    suspend fun ObjetivoPorUsuario(id:Int): List<Objetivo>{
        return ApiClient.api.getObjetivosPorUsuarios(id)
    }
    suspend fun postObjetivo(objetivo: ObjetivoDTO){
        return ApiClient.api.postObjetivos(objetivo)
    }
}