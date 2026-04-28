package com.example.habitforge.data.repository

import com.example.habitforge.data.api.ApiClient
import com.example.habitforge.model.LoginRequest
import com.example.habitforge.model.Meta

class MetaRepository {
    suspend fun MetaPorUsuario(id:Int): List<Meta>{
        return ApiClient.api.getMetasPorUsuarios(id)
    }
}