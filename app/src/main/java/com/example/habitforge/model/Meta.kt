package com.example.habitforge.model

data class Meta (
    val id: Int,
    val nome: String,
    val Valor_total: Float,
    val Valor_inicial: Float,
    val Valor_atual: Float,
    val status: Boolean,
    val usuario: Usuario
)
