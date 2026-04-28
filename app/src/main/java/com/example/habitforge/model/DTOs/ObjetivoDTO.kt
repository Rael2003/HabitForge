package com.example.habitforge.model.DTOs

import com.example.habitforge.model.Usuario

data class ObjetivoDTO (
    val id: Int,
    val nome: String,
    val valor: Float,
    val usuario: Int
)
