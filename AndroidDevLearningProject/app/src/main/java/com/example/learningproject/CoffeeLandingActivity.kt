package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityCoffeeLandingBinding

class CoffeeLandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoffeeLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCoffeeLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        binding.btnGetStarted.setOnClickListener {
            val sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putBoolean("onBoardingDone", true)
                apply()
            }

            val intent = Intent(this, CoffeeListActivity::class.java)
            startActivity(intent)
        }
    }
}