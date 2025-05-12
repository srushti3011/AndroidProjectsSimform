package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityViewPagerRecyclerAdapterBinding

class ViewPagerRecyclerAdapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerRecyclerAdapterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewPagerRecyclerAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textCollection: List<String> = listOf(
            "String One",
            "String Two",
            "String Three",
            "String Four"
        )
        binding.viewPager.adapter = ViewPagerRecyclerAdapter(textCollection)
    }
}