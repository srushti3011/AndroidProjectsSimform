package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityMainBinding
import com.example.learningproject.databinding.ActivityWidgetsHomeBinding

class WidgetsHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWidgetsHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWidgetsHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGoToWidgetsOne.setOnClickListener {
            val intent = Intent(this, WidgetsOneActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToCustomSnackBar.setOnClickListener {
            val intent = Intent(this, CustomSnackBarActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToTextViews.setOnClickListener {
            val intent = Intent(this, TextViews::class.java)
            startActivity(intent)
        }

        binding.btnGoToMaterialViews.setOnClickListener {
            val intent = Intent(this, MaterialViewsActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToEditTextTypes.setOnClickListener {
            val intent = Intent(this, EditTextTypesActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToCustomToast.setOnClickListener {
            val intent = Intent(this, CustomToastActivity::class.java)
            startActivity(intent)
        }
    }
}