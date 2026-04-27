package com.example.habitforge.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.habitforge.R
import com.example.habitforge.data.repository.UsuarioRepository
import com.example.habitforge.model.Usuario
import com.example.habitforge.ui.Goal
import com.example.habitforge.ui.MainFrame
import com.example.habitforge.ui.Objective

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.usuario.observe(this) { usuario ->
            usuario?.let {
                println("Login OK: ${it.id}")

                startActivity(Intent(this, MainFrame::class.java))
            }
        }

        // 👀 observa erro
        viewModel.erro.observe(this) {
            println(it)
        }

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })

        btnLogin.setOnClickListener {
            val login = findViewById<EditText>(R.id.txtEmail).text.toString()
            val senha = findViewById<EditText>(R.id.txtSenha).text.toString()

            viewModel.login(login, senha)
        }
    }

    fun login(view: View?) {
        val login = findViewById<EditText>(R.id.txtEmail).text.toString()
        val senha = findViewById<EditText>(R.id.txtSenha).text.toString()
        viewModel.login(login, senha)
    }

    fun goal(view: View?) {
        startActivity(Intent(this, Goal::class.java))
    }

    fun objective(view: View?) {
        startActivity(Intent(this, Objective::class.java))
    }
}