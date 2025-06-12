package com.example.learningproject

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.NetworkCallFiles.ApiState
import com.example.learningproject.NetworkCallFiles.OkhttpApiInfoEndpoints
import com.example.learningproject.databinding.ActivityOkhttpPracticeBinding

class OkhttpPracticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOkhttpPracticeBinding
    val viewModel: OkHttpPracticeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOkhttpPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
            btnMakeSimpleOkhttpRequest.setOnClickListener {
                viewModel.getSingleUser()
            }

            btnLoginUser.setOnClickListener {
                viewModel.loginUser()
            }

            btnDeleteUser.setOnClickListener {
                viewModel.deleteUser()
            }
        }

        viewModel.singleUserState.observe(this, {
            Log.i("TAG", "The state is ${it}")
            when (it) {
                ApiState.Loading -> binding.loadingView.visibility = View.VISIBLE
                ApiState.Idle -> binding.loadingView.visibility = View.GONE
                ApiState.Success -> {
                    binding.apply {
                        loadingView.visibility = View.GONE
                        val fullName = "${viewModel.singleUserResponse.value?.data?.firstName} " +
                                "${viewModel.singleUserResponse.value?.data?.lastName}"
                        tvFullNameResponse.text = fullName
                    }
                }
                ApiState.Error -> {
                    binding.loadingView.visibility = View.GONE
                }
            }
        })
    }
}