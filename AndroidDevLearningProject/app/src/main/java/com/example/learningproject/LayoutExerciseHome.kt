package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityLayoutExerciseHomeBinding

class LayoutExerciseHome : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutExerciseHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLayoutExerciseHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGoToExerciseLogin.setOnClickListener {
            val intent = Intent(this, LayoutExerciseLoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToExerciseSignup.setOnClickListener {
            val intent = Intent(this, LayoutExerciseSignupActivity::class.java)
            startActivity(intent)
        }
    }
}