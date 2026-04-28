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
import com.example.habitforge.R.id.btnConfirmarMeta

class Goal : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_goal)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val btn = findViewById<Button>(btnConfirmarMeta)

        btn.setOnClickListener {
            confirmarMeta()
        }
    }

    fun confirmarMeta(){
        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val usuarioId = sharedPreferences.getInt("usuario_id", -1)
        viewModel.confirmarMeta(R.id.txtTitulo.toString(), R.id.txtMetaInicial.toFloat(),R.id.txtMeta.toFloat(), usuarioId);
    }
}