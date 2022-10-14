package com.example.firebasetutorial

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
        setContentView(R.layout.activity_home)
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        //setup
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"
        
        binding.run { 
            emailTv.text = email
            providerTv.text = provider
        }

        binding.close.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}