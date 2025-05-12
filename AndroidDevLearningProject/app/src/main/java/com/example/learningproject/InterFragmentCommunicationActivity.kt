package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import com.example.learningproject.databinding.ActivityInterFragmentCommunicationBinding

class InterFragmentCommunicationActivity : AppCompatActivity(), DataAndResultCommunicator {

    private lateinit var binding: ActivityInterFragmentCommunicationBinding
    private val fragmentManager = supportFragmentManager
    private val result = arrayOf(95, 97, 50, 45, 80)
    val dataFrag = StudentDataInputFragment()
    val resultFrag = ShowResultFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInterFragmentCommunicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val transactionOne = fragmentManager.beginTransaction()
        transactionOne
            .add(R.id.fragmentTakeData, dataFrag)
            .commit()

        val transactionTwo = fragmentManager.beginTransaction()
        transactionTwo
            .add(R.id.fragmentShowResult, resultFrag)
            .commit()
    }

    override fun passIDNum(id: Int) {
        val bundle = Bundle()
        bundle.putInt("idNum", id)
        bundle.putInt("marks", result[id-1])
        resultFrag.arguments = bundle
        resultFrag.updateUI()
    }
}