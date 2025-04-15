package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MaterialViewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_material_views)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputPassword = findViewById<TextInputEditText>(R.id.textInputPassword)
        val inputPasswordLayout = findViewById<TextInputLayout>(R.id.textInputLayoutPassword)
        inputPassword.addTextChangedListener {
            val text = inputPassword.text.toString()
            if (text.length > 8) {
                inputPasswordLayout.setError("Length is exceeding the allowed length")
            } else {
                inputPasswordLayout.error = null
            }
        }
    }
}