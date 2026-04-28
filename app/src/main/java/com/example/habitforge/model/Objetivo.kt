package com.example.habitforge.model

data class Objetivo (
    val id: Int,
    val nome: String,
    val valor: Float,
    val usuario: Usuario
)
