package com.example.habitforge.ui.login

import androidx.lifecycle.*
import com.example.habitforge.data.repository.UsuarioRepository
import com.example.habitforge.model.Usuario
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = UsuarioRepository()

    private val _usuario = MutableLiveData<Usuario?>()
    val usuario: LiveData<Usuario?> = _usuario

    private val _erro = MutableLiveData<String>()
    val erro: LiveData<String> = _erro

    fun login(login: String, senha: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(login, senha)
                _usuario.value = response
            } catch (e: Exception) {
                println(e.toString())
                _erro.value = "Login inválido"
            }
        }
    }
}