package com.example.learningproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityComputeFullNameBinding

class ComputeFullNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComputeFullNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityComputeFullNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val firstName = intent.extras?.getString("firstName")
        val lastName = intent.extras?.getString("lastName")
        binding.btnComputeFullName.setOnClickListener {
            val fullName = "$firstName $lastName" ?: ""
            val resultIntent = Intent(this, IntentForResultActivity::class.java)
            resultIntent.putExtra("fullName", fullName)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}