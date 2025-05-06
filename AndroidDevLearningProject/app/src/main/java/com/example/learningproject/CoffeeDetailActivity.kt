package com.example.learningproject

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityCoffeeDetailBinding
class CoffeeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoffeeDetailBinding
    private var categoryInd: Int? = null
    private var coffeeInd: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCoffeeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.white))
        )
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.back_arrow)
            setDisplayShowTitleEnabled(true)
            title = "Details"
        }
//        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)
//        supportActionBar?.setDisplayShowTitleEnabled(true)
//        supportActionBar?.title = "Details"

        val coffee = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("selectedCoffee", CoffeeItem::class.java)
        } else {
            intent.getSerializableExtra("user")
        } as CoffeeItem

        categoryInd = intent.getIntExtra("categoryInd", -1)
        coffeeInd = intent.getIntExtra("coffeeInd", -1)
        Log.i("categoryChanged", "${coffee.toString()} inside details")
        binding.apply {
            imgCoffee.setImageResource(coffee.imgName)
            tvCoffeeName.text = coffee.coffeeName
            tvCoffeeDesc.text = coffee.descriptionSmall
            tvDescriptionContent.text = coffee.descriptionDetail
            tvCoffeePrice.text = "$ ${coffee.price}"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.coffee_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.like_coffee -> {
                coffeeDetails[categoryInd ?: 0]
                    .getValue(coffeeCategories[categoryInd ?: 0])[coffeeInd ?: 0]
                        .isLiked = true
                Log.i("categoryChanged", coffeeDetails.toString())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}