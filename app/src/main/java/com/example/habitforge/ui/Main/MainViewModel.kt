package com.example.habitforge.ui.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitforge.data.repository.MetaRepository
import com.example.habitforge.data.repository.UsuarioRepository
import com.example.habitforge.model.Meta
import com.example.habitforge.model.Usuario
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private val repository = MetaRepository()

    private val _meta = MutableLiveData<List<Meta>>()
    val meta: LiveData<List<Meta>> = _meta

    private val _erro = MutableLiveData<String>()
    val erro: LiveData<String> = _erro

    fun MetaPorUsuario(id:Int) {
        viewModelScope.launch {
            try {
                val response = repository.MetaPorUsuario(id)
                _meta.value = response
            } catch (e: Exception) {
                println(e.toString())
                _erro.value = "Não há meta"
            }
        }
    }
}