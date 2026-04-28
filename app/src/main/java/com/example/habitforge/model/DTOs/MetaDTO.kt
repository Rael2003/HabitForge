package com.example.habitforge.model.DTOs

import com.example.habitforge.model.Usuario

data class MetaDTO (
    val id: Int,
    val nome: String,
    val Valor_total: Float,
    val Valor_inicial: Float,
    val Valor_atual: Float,
    val status: Boolean,
    val usuario: Int
)
