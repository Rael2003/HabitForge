package com.example.habitforge.ui.Main

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.habitforge.R

class Objective : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_objective)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val btn = findViewById<Button>(R.id.btnConfirmar)

        btn.setOnClickListener {
            confirmarObjetivo()
        }
    }

    fun confirmarObjetivo(){
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val usuarioId = sharedPreferences.getInt("usuario_id", -1)
        viewModel.confirmarObjetivo(R.id.txtNomeObjeto.toString(), R.id.txtValor.toFloat(), usuarioId);
    }
}