package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityWebServicesPracticeHomeBinding

class WebServicesPracticeHome : AppCompatActivity() {

    private lateinit var binding: ActivityWebServicesPracticeHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWebServicesPracticeHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            btnGoToHTTPURLConectionHome.setOnClickListener {
                val intent = Intent(
                    this@WebServicesPracticeHome,
                    HTTPURLConnectionHome::class.java
                )
                startActivity(intent)
            }

            btnGoToJsonParsingPractice.setOnClickListener {
                val intent = Intent(
                    this@WebServicesPracticeHome,
                    JsonParsingActivity::class.java
                )
                startActivity(intent)
            }

            btnGoToOkhttpPractice.setOnClickListener {
                val intent = Intent(
                    this@WebServicesPracticeHome,
                    OkhttpPracticeActivity::class.java
                )
                startActivity(intent)
            }

            btnGoToRetrofitPractice.setOnClickListener {
                val intent = Intent(
                    this@WebServicesPracticeHome,
                    RetrofitPracticeActivity::class.java
                )
                startActivity(intent)
            }

            btnGoToRetrofitPracticeWithInjection.setOnClickListener {
                val intent = Intent(
                    this@WebServicesPracticeHome,
                    RetrofitWithInjectionActivity::class.java
                )
                startActivity(intent)
            }
        }
    }
}