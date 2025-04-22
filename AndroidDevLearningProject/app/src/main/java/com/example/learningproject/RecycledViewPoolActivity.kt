package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningproject.databinding.ActivityRecycledViewPoolBinding

class RecycledViewPoolActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecycledViewPoolBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecycledViewPoolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val contentList = List(100) { "item $it" }

        val pool = RecyclerView.RecycledViewPool()
        pool.setMaxRecycledViews(R.layout.simple_rv_item, 5)
        binding.rvOne.layoutManager = LinearLayoutManager(this)
        binding.rvOne.adapter = RecycledViewPoolAdapter(contentList)
//        {
//            Log.i("RecyclerView", pool.getRecycledView().toString())
//        }
        binding.rvTwo.layoutManager = LinearLayoutManager(this)
        binding.rvTwo.adapter = RecycledViewPoolAdapter(contentList)
//        {
//            Log.i("RecyclerView", pool.getRecycledView().toString())
//        }
        binding.rvOne.setRecycledViewPool(pool)
        binding.rvTwo.setRecycledViewPool(pool)
    }
}