package com.example.learningproject

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.navigation.findNavController
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
                .addToBackStack("addedTwo")
//                .addToBackStack("addedOne")
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }
// addedOne addedTwo addedOne

//        1 same 2 same
//        1 same same 2
//        1 2 same same
//        same 1 2
//        same 1 same 2
//        same same 1
        binding.btnAddFragTwo.setOnClickListener {
            val transaction = supportFragManager.beginTransaction()
            transaction.add(R.id.fragmentContainer, fragmentTwo, "two")
//                .addToBackStack("addedOne")
                .addToBackStack("addedOne")
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }

        binding.btnReplaceOneTwo.setOnClickListener {
            val transaction = supportFragManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, fragmentTwo)
//                .addToBackStack("replacedOneWithTwo")
                .addToBackStack("addedOne")
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }

        binding.btnDetachTwo.setOnClickListener {
            val transaction = supportFragManager.beginTransaction()
            transaction.detach(fragmentTwo)
                .commit()
            Log.i("Transaction", "${supportFragManager.fragments}")
        }
// A B C B D
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
            Log.i("Transaction", "${supportFragManager.backStackEntryCount}")
        }

        binding.btnPopOne.setOnClickListener {
            Log.i("Transaction", "${supportFragManager.fragments}")
            supportFragManager.popBackStack("addedOne", FragmentManager.POP_BACK_STACK_INCLUSIVE)
//            finishAffinity()
        }

        binding.btnAddMore.setOnClickListener {
            val thirdFrag = ThirdFragment()
            Log.i("Transaction", "${supportFragManager.fragments}")
            val transaction = supportFragManager.beginTransaction()
            transaction.add(R.id.fragmentContainer, thirdFrag)
                .addToBackStack("addedThree")
                .commit()
        }
    }
}