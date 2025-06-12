package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningproject.RetrofitWithInjection.UserListReqres
import com.example.learningproject.RetrofitWithInjection.UserListRetrofitResponse
import com.example.learningproject.databinding.ActivityUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserListBinding
    private val viewModel: UserListVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.rvUserList.layoutManager = LinearLayoutManager(this@UserListActivity)
        val adapter = UserListFromApiAdapter { id ->
            val intent = Intent(this, UserDetailFromAPIActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
        binding.rvUserList.adapter = adapter
        viewModel.getListOfUsers()
        viewModel.userListState.observe(this, {
            binding.apply {
                when (it) {
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
                        Log.i("TAG", "In success activity")
                        adapter.users = (it.data as UserListRetrofitResponse).data
                    }
                }
            }
        })
        binding.btnCreateUser.setOnClickListener {
            viewModel.createUser()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}