package com.example.habitforge.data.repository

import com.example.habitforge.data.api.ApiClient
import com.example.habitforge.model.Usuario

class UsuarioRepository {
    suspend fun listarUsuarios(): List<Usuario> {
        return ApiClient.api.getUsuarios();
    }
}