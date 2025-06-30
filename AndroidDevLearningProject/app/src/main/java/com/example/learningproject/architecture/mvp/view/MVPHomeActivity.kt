package com.example.learningproject.architecture.mvp.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.R
import com.example.learningproject.architecture.mvp.model.MVPAPIService
import com.example.learningproject.architecture.mvp.model.UserModel
import com.example.learningproject.architecture.mvp.presenter.MVCHomePresenter
import com.example.learningproject.architecture.mvp.presenter.MVCViewUpdatable
import com.example.learningproject.databinding.ActivityMvphomeBinding

class MVPHomeActivity : AppCompatActivity(), MVCViewUpdatable {

    val presenter = MVCHomePresenter(this)
    private lateinit var binding: ActivityMvphomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMvphomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            btnGetUser.setOnClickListener {
                presenter.getUser()
            }
            btnComputeFullName.setOnClickListener {
                presenter.computeFullName()
            }
        }
    }

    override fun updateViewWithUser(user: UserModel) {
        binding.tvButtonResult.text = user.toString()
    }

    override fun updateViewWithFullName(fullName: String) {
        binding.tvButtonResult.text = fullName
    }
}