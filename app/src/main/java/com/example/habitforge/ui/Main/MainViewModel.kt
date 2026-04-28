package com.example.habitforge.ui.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitforge.data.repository.MetaRepository
import com.example.habitforge.data.repository.ObjetivoRepository
import com.example.habitforge.model.DTOs.MetaDTO
import com.example.habitforge.model.DTOs.ObjetivoDTO
import com.example.habitforge.model.Meta
import com.example.habitforge.model.Objetivo
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private val metarepository = MetaRepository()
    private val objetivorepository = ObjetivoRepository()

    private val _objetivo = MutableLiveData<List<Objetivo>>()
    val objetivo: LiveData<List<Objetivo>> = _objetivo

    private val _meta = MutableLiveData<List<Meta>>()
    val meta: LiveData<List<Meta>> = _meta

    private val _erro = MutableLiveData<String>()
    val erro: LiveData<String> = _erro

    fun MetaPorUsuario(id:Int) {
        viewModelScope.launch {
            try {
                val response = metarepository.MetaPorUsuario(id)
                _meta.value = response
            } catch (e: Exception) {
                println(e.toString())
                _erro.value = "Não há meta"
            }
        }
    }

    fun ObjetivoPorUsuario(id:Int) {
        viewModelScope.launch {
            try {
                val response = objetivorepository.ObjetivoPorUsuario(id)
                _objetivo.value = response
            } catch (e: Exception) {
                println(e.toString())
                _erro.value = "Não há objetivo"
            }
        }
    }

    fun confirmarObjetivo(nome: String, valor: Float, id:Int ){
        viewModelScope.launch {
            try {
                val obj = ObjetivoDTO(0,nome,valor, id)
                objetivorepository.postObjetivo(obj)
            }catch (e: Exception){
                println(e.toString())
                _erro.value = "Não funfo"
            }
        }
    }

    fun confirmarMeta(nome: String, inicial: Float,final: Float, id:Int ){
        viewModelScope.launch {
            try {
                val obj = MetaDTO(0, nome, final, inicial, inicial, false, id)
                metarepository.postMeta(obj)
            }catch (e: Exception){
                println(e.toString())
                _erro.value = "Não funfo"
            }
        }
    }
}