package com.example.learningproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityIntentForResultBinding

class IntentForResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntentForResultBinding

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            binding.tvFullName.text = result.data?.getStringExtra("fullName")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIntentForResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnComputeFullName.setOnClickListener {
            val intent = Intent(this, ComputeFullNameActivity::class.java)
            intent.putExtra("firstName", binding.etFirstName.text.toString())
            intent.putExtra("lastName", binding.etLastName.text.toString())
            resultLauncher.launch(intent)
        }
    }
}