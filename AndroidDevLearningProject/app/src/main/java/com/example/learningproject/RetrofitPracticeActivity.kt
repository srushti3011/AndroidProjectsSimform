package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityRetrofitPracticeBinding

class RetrofitPracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitPracticeBinding
    val viewModel: RetrofitPracticeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRetrofitPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.apply {
            btnGetSingleUser.setOnClickListener {
                viewModel.getSingleUser()
            }

            btnGetUserList.setOnClickListener {
                viewModel.getUserList()
            }

            btnDeleteUser.setOnClickListener {
                viewModel.deleteUser()
            }

            btnLoginUser.setOnClickListener {
                viewModel.loginUser()
            }
        }
        viewModel.loginState.observe(this, {
            binding.apply {
                val idleState = "Idle state"
                val loadingState = "Loading state"
                when (it) {
                    is RetrofitApiStateNormal.Error -> tvLoginState.text = it.errorType.message
                    is RetrofitApiStateNormal.Idle -> tvLoginState.text = idleState
                    is RetrofitApiStateNormal.Loading -> tvLoginState.text = loadingState
                    is RetrofitApiStateNormal.Success<*> -> tvLoginState.text = it.data.toString()
                }
            }
        })
    }
}