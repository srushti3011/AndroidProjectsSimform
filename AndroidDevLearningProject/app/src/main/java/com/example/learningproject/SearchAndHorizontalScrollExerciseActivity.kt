package com.example.learningproject

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningproject.databinding.ActivitySearchAndHorizontalScrollExerciseBinding

class SearchAndHorizontalScrollExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchAndHorizontalScrollExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchAndHorizontalScrollExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productAdapter = ProductAdapter(productInfo)
        binding.apply {
            rvProducts.apply {
                layoutManager = LinearLayoutManager(
                    this@SearchAndHorizontalScrollExerciseActivity
                )
                adapter = productAdapter
            }
            searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    productAdapter.filter.filter(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    productAdapter.filter.filter(newText)
                    return true
                }
            })
            btnRemoveFilter.setOnClickListener {
                productAdapter.filter.filter("")
            }
            btnGreaterThanThousand.setOnClickListener {
                productAdapter.filterGtThousand()
            }
            btnFivestar.setOnClickListener {
                productAdapter.filterFivestar()
            }
            btnHighestPrice.setOnClickListener {
                productAdapter.filterHighestPrice()
            }
            btnMostLiked.setOnClickListener {
                productAdapter.filterMostLiked()
            }
        }
    }
}