package com.example.learningproject

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityRetrofitWithInjectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitWithInjectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitWithInjectionBinding
    private val viewModel: RetrofitWithInjectionVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRetrofitWithInjectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.loginUser()
        viewModel.apiState.observe(this, {
            binding.apply {
                when (it) {
                    RetrofitStateAPI.Idle,
                    RetrofitStateAPI.Succcess,
                    RetrofitStateAPI.Error -> loadingView.visibility = View.GONE
                    RetrofitStateAPI.Loading -> loadingView.visibility = View.VISIBLE
                }
            }
        })
    }
}