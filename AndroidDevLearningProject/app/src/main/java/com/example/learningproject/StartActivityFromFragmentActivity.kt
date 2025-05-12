package com.example.learningproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityStartFromFragmentBinding

class StartActivityFromFragmentActivity : AppCompatActivity() {

    private val TAG = "tag"
    private lateinit var binding: ActivityStartFromFragmentBinding
    private val viewModel: FragmentStartActivityForResultVM by viewModels()

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.i(TAG, "Got result")
            result.data?.getStringExtra("fullName")?.let { viewModel.setResultValue(it) }
        }
    }

    private val fragment = StartActivityForResultFragment() {
        val intent = Intent(this, ComputeFullNameActivity::class.java)
        intent.putExtra("firstName", "first name")
        intent.putExtra("lastName", "last name")
        resultLauncher.launch(intent)
    }

    private val fragManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStartFromFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val transaction = fragManager.beginTransaction()
        transaction.add(R.id.fragContainer, fragment)
            .commit()
    }
}