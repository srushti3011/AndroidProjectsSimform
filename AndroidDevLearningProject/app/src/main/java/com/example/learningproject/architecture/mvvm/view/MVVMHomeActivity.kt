package com.example.learningproject.architecture.mvvm.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.R
import com.example.learningproject.architecture.mvvm.viewmodel.MVVMHomeViewModel
import com.example.learningproject.architecture.mvvmNetwork.SignUpDetailsModel
import com.example.learningproject.databinding.ActivityMvvmhomeBinding

class MVVMHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMvvmhomeBinding
    private val viewModel: MVVMHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMvvmhomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            btnSignUp.setOnClickListener {
                val signUpDetails = SignUpDetailsModel(
                    firstName = etFirstName.toString(),
                    lastName = etLastName.toString(),
                    username = etUsername.toString(),
                    password = etPassword.toString()
                )
                viewModel.callUserSignup(signUpDetails)
            }
        }

        viewModel.userCreated.observe(this, {
            if (it) {
                Toast
                    .makeText(
                        this, "User created successfully", Toast.LENGTH_SHORT
                    )
                    .show()
                binding.apply {
                    arrayOf(etFirstName, etLastName, etUsername, etPassword).forEach {
                        it.text?.clear()
                        it.clearFocus()
                    }
                }
            } else {
                Toast
                    .makeText(
                        this, "User creation problem", Toast.LENGTH_SHORT
                    )
                    .show()
            }
        })
    }
}