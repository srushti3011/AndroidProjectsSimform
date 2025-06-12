package com.example.learningproject

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.learningproject.RetrofitWithInjection.SingleUserRetrofitResponse
import com.example.learningproject.databinding.ActivityUserDetailFromApiactivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFromAPIActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailFromApiactivityBinding
    private val viewModel: UserDetailFromApiVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserDetailFromApiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getIntExtra("id", 1)
        viewModel.getSingleUser(id)
        viewModel.singleUserState.observe(this, {
            binding.apply {
                when(it) {
                    is ExerciseApiState.Error,
                    is ExerciseApiState.ExceptionState,
                    is ExerciseApiState.Idle -> {
                        progressBar.visibility = View.GONE
                    }
                    is ExerciseApiState.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is ExerciseApiState.Success<*> -> {
                        progressBar.visibility = View.GONE
                        val receivedData = (it.data as SingleUserRetrofitResponse).data
                        tvUserId.text = receivedData.id.toString()
                        tvFullName.text = receivedData.firstName + " " + receivedData.lastName
                        tvEmail.text = receivedData.email
                        Glide
                            .with(this@UserDetailFromAPIActivity)
                            .load(it.data.data.avatar)
                            .centerCrop()
                            .into(imgUserAvatar)
                    }
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}