package com.example.learningproject

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.learningproject.databinding.ActivityFlexibleUiactivityBinding

class FlexibleUIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlexibleUiactivityBinding
    private val fragManager = supportFragmentManager
    private lateinit var btnFrag: OneTransactionFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFlexibleUiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i("FlexibleUI", "${binding.root.id == R.id.main}")
        if (supportFragmentManager.findFragmentByTag("OneTransactionFragment") == null) {
            btnFrag = OneTransactionFragment()
            val transaction = fragManager.beginTransaction()
            transaction
                .add(R.id.fragContainer, btnFrag, "OneTransactionFragment")
            transaction.commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (binding.landscapeSecondFrag != null) {
            navController = findNavController(R.id.landscapeSecondFrag)
        }
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}