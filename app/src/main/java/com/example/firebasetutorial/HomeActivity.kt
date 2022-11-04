package com.example.firebasetutorial

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasetutorial.databinding.ActivityAuthBinding
import com.example.firebasetutorial.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        //setup
        setup(email ?: "", provider ?: "")

        //guardado de datos
        val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun setup(email: String, provider: String) = binding.run {
        title = "Inicio"

        emailTv.text = email
        providerTv.text = provider

        close.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            onBackPressed()
        }
    }
}