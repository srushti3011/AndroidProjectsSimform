package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.example.learningproject.databinding.ActivityInterFragCommUsingVmactivityBinding

class InterFragCommUsingVMActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInterFragCommUsingVmactivityBinding
    val fragManager = supportFragmentManager
    val selectFrag = SelectionFragment()
    val showImgFrag = ShowImageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInterFragCommUsingVmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val transactionOne = fragManager.beginTransaction()
        transactionOne
            .add(R.id.fragmentContainerOne, selectFrag)
            .commit()
        val transactionTwo = fragManager.beginTransaction()
        transactionTwo
            .add(R.id.fragmentContainerTwo, showImgFrag)
            .commit()
    }
}