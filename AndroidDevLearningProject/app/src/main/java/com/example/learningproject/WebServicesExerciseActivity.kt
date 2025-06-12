package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.RetrofitWithInjection.LoginUserRetrofitBody
import com.example.learningproject.RetrofitWithInjection.LoginUserRetrofitResponse
import com.example.learningproject.databinding.ActivityWebServicesExerciseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebServicesExerciseActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWebServicesExerciseBinding
    private val viewModel: WebServicesExerciseVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWebServicesExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.apply {
            etPassword.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    etPasswordLayout.error = null
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            etEmail.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    etEmailLayout.error = null
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            btnLogin.setOnClickListener {
                val emailEntered = etEmail.text.toString()
                val passwordEntered = etPassword.text.toString()

                if (emailEntered.isEmpty()) {
                    etEmailLayout.error = "Email is empty"
                } else if (passwordEntered.isEmpty()) {
                    etPasswordLayout.error = "Password is empty"
                } else {
                    viewModel.loginUser(LoginUserRetrofitBody(emailEntered, passwordEntered))
                }
            }
        }

        viewModel.loginState.observe(this, {
            binding.apply {
                when (it) {
                    is ExerciseApiState.Error -> {
                        AlertDialog.Builder(this@WebServicesExerciseActivity)
                            .setTitle("Error")
                            .setMessage(
                                (viewModel.loginState.value as ExerciseApiState.Error).error
                            )
                            .setPositiveButton("Ok") { dialog, which ->
                                dialog.cancel()
                            }
                            .create()
                            .show()
                    }
                    is ExerciseApiState.ExceptionState -> {}
                    is ExerciseApiState.Idle -> {}
                    is ExerciseApiState.Success<*> -> {
                        Log.i("TAG", "Login successful")
                        val sharedPreferences = getSharedPreferences(
                            "UserPreference",
                            MODE_PRIVATE
                        )
                        val editor = sharedPreferences.edit()
                        editor.putString(
                            "userToken",
                            (it.data as LoginUserRetrofitResponse).token
                        )
                        editor.apply()
                        val intent = Intent(
                            this@WebServicesExerciseActivity,
                            UserListActivity::class.java
                        )
                        startActivity(intent)
                        finish()
                    }
                    is ExerciseApiState.Loading -> {
                        Log.i("TAG", "Loading")
                    }
                    else -> {}
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}