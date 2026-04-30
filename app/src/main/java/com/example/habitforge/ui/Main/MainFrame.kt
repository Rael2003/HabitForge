package com.example.habitforge.ui.Main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.habitforge.R
import com.example.habitforge.ui.Main.Fragment.HomeFragment
import com.example.habitforge.ui.Main.Fragment.ProfileFragment
import com.example.habitforge.ui.Main.Fragment.SettingsFragment
import com.example.habitforge.ui.login.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFrame : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_frame)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main),
            OnApplyWindowInsetsListener { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
                )
                insets
            }
        )

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val usuarioId = sharedPreferences.getInt("usuario_id", -1)

        if (usuarioId != -1) {
            viewModel.MetaPorUsuario(usuarioId)
            viewModel.ObjetivoPorUsuario(usuarioId)
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Fragment inicial
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_profile -> loadFragment(ProfileFragment())
                R.id.nav_settings -> loadFragment(SettingsFragment())
            }
            true
        }
    }

    fun goal(view: View?) {
        startActivity(Intent(this, Goal::class.java))
    }

    fun objective(view: View?) {
        startActivity(Intent(this, Objective::class.java))
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}