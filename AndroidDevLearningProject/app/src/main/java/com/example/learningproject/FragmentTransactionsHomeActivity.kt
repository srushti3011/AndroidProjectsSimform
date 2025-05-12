package com.example.learningproject

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.learningproject.databinding.ActivityFragmentTransactionsHomeBinding

class FragmentTransactionsHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentTransactionsHomeBinding
    private val supportFragManager = supportFragmentManager
    private val fragmentOne = OneTransactionFragment()
    private val fragmentTwo = TwoTransactionFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFragmentTransactionsHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAddFragOne.setOnClickListener {
            val transaction = supportFragManager.beginTransaction()
            transaction.add(R.id.fragmentContainer, fragmentOne, "one")
                .addToBackStack("addedOne")
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }

        binding.btnAddFragTwo.setOnClickListener {
            val transaction = supportFragManager.beginTransaction()
            transaction.add(R.id.fragmentContainer, fragmentTwo, "two")
                .addToBackStack("addedTwo")
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }

//        binding.btnAddFragTwo.setOnClickListener {
//            val transaction = supportFragManager.beginTransaction()
//            transaction.add(R.id.frag, fragmentOne, "two").addToBackStack("addedOne").commit()
//            Log.i("Transaction", "${supportFragManager.fragments}")
//        }

        binding.btnReplaceOneTwo.setOnClickListener {
            val transaction = supportFragManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragmentTwo)
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }

        binding.btnDetachTwo.setOnClickListener {
            val transaction = supportFragManager.beginTransaction()
            transaction.detach(fragmentTwo)
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }

        binding.btnRemoveTwo.setOnClickListener {
            val transaction = supportFragManager.beginTransaction()
            transaction.remove(fragmentTwo)
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }

        binding.btnHideTwo.setOnClickListener {
            val transaction = supportFragManager.beginTransaction()
            transaction.hide(fragmentTwo)
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }
    }
}