package com.example.learningproject

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningproject.databinding.ActivitySearchViewBinding

class SearchViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameList = listOf("Fruits", "Table", "Chair", "Food", "Medicine", "Car")
        val adapter = NameRecyclerAdapter(
            nameList
        )

        binding.rvItems.layoutManager = LinearLayoutManager(this)
        binding.rvItems.adapter = adapter
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        binding.apply {
            btnContainsA.setOnClickListener {
                adapter.filter.filter("a")
            }
            btnLenGtFive.setOnClickListener {
                adapter.lengthGtFive()
            }
            btnClearFilter.setOnClickListener {
                adapter.filter.filter("")
            }
            btnContainsD.setOnClickListener {
                adapter.containsD()
            }
        }
    }
}