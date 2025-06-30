package com.example.learningproject.architecture.mvc.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.R
import com.example.learningproject.architecture.mvc.model.MVCAPIService
import com.example.learningproject.architecture.mvc.model.NumberModel
import com.example.learningproject.databinding.ActivityMvchomeBinding
import java.util.Locale

class MVCHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvchomeBinding
    private lateinit var numberResponse: NumberModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMvchomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            btnGetNumber.setOnClickListener {
                numberResponse = MVCAPIService.shared.getNumber()
                tvBtnResult.text = String.format(
                    Locale.ENGLISH,
                    "%d",
                    numberResponse.number
                )
            }

            btnDivideByTwo.setOnClickListener {
                tvBtnResult.text = String.format(
                    Locale.ENGLISH,
                    "%.1f",
                    numberResponse.divideByTwo()
                )
            }
        }
    }
}