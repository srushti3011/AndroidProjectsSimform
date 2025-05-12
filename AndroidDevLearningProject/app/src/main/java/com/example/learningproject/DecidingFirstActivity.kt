package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DecidingFirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deciding_first)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()
        val sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        val onBoardingDone = sharedPreferences.getBoolean("onBoardingDone", false)
        if (onBoardingDone) {
            val intent = Intent(this, CoffeeListActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, CoffeeLandingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}