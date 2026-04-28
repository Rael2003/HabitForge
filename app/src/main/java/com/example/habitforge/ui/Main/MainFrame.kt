package com.example.habitforge.ui.Main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.habitforge.R
import kotlinx.coroutines.launch
import com.example.habitforge.model.Meta
import com.example.habitforge.ui.login.MainActivity

class MainFrame : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main_frame)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val usuarioId = sharedPreferences.getInt("usuario_id", -1)
        val containerMetas = findViewById<LinearLayout>(R.id.containerMetas)
        val containerObjetivo = findViewById<LinearLayout>(R.id.containerObjetivos)

        viewModel.meta.observe(this) { metas ->
            metas?.let {
                println("Carregou metas: ${it.size}")

                // Limpa antes para evitar duplicação
                containerMetas.removeAllViews()

                for (meta in metas) {
                    val checkBox = CheckBox(this)
                    checkBox.text = meta.nome // ou meta.nome dependendo do seu model
                    checkBox.isChecked = meta.status // opcional

                    // Evento ao marcar/desmarcar
                    checkBox.setOnCheckedChangeListener { _, isChecked ->
                        println("Meta ${meta.id} alterada para: $isChecked")
                    }

                    containerMetas.addView(checkBox)
                }
            }
        }

        viewModel.objetivo.observe(this) { objetivos ->
            objetivos?.let {
                println("Carregou objetivos: ${it.size}")

                // Limpa antes para evitar duplicação
                containerObjetivo.removeAllViews()

                for (obj in objetivos) {
                    val checkBox = CheckBox(this)
                    checkBox.text = obj.nome // ou meta.nome dependendo do seu model

                    // Evento ao marcar/desmarcar
                    checkBox.setOnCheckedChangeListener { _, isChecked ->
                        println("Meta ${obj.id} alterada para: $isChecked")
                    }

                    containerObjetivo.addView(checkBox)
                }
            }
        }

        if (usuarioId != -1) {
            viewModel.MetaPorUsuario(usuarioId)
            viewModel.ObjetivoPorUsuario(usuarioId)
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
    }

    fun goal(view: View?) {
        startActivity(Intent(this, Goal::class.java))
    }

    fun objective(view: View?) {
        startActivity(Intent(this, Objective::class.java))
    }
}