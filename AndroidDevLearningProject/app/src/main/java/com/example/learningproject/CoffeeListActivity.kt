package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityCoffeeLandingBinding
import com.example.learningproject.databinding.ActivityCoffeeListBinding

class CoffeeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoffeeListBinding
    private val fragManager = supportFragmentManager
    private val coffeeHomeFragment = CoffeeHomeFragment()
    private val coffeeLikedFragment = LikeFragment()
    private val coffeeCartFragment = CartFragment()
    private val coffeeNotificationFragment = NotificationFragment()
    private val viewModel: CategoryCoffeeListCommunicationVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCoffeeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        fragManager.beginTransaction().apply {
            add(R.id.coffeeFragBView, coffeeHomeFragment)
            add(R.id.coffeeFragBView, coffeeLikedFragment)
            add(R.id.coffeeFragBView, coffeeCartFragment)
            add(R.id.coffeeFragBView, coffeeNotificationFragment)
            hide(coffeeLikedFragment)
            hide(coffeeCartFragment)
            hide(coffeeNotificationFragment)
                .commit()

        binding.coffeeBottomNav.setOnItemSelectedListener { selectedItem ->
            when (selectedItem.itemId) {
                R.id.coffeeHome -> {
                    fragManager.beginTransaction().apply {
                        hide(coffeeLikedFragment)
                        hide(coffeeCartFragment)
                        hide(coffeeNotificationFragment)
                        show(coffeeHomeFragment)
                            .commit()
                    }
                    true
                }
                R.id.coffeeLiked -> {
                    fragManager.beginTransaction().apply {
                        hide(coffeeHomeFragment)
                        hide(coffeeCartFragment)
                        hide(coffeeNotificationFragment)
                        show(coffeeLikedFragment)
                            .commit()
                    }
                    true
                }
                R.id.coffeeCart -> {
                    fragManager.beginTransaction().apply {
                        hide(coffeeHomeFragment)
                        hide(coffeeLikedFragment)
                        hide(coffeeNotificationFragment)
                        show(coffeeCartFragment)
                            .commit()
                    }
                    true
                }
                R.id.coffeeNotification -> {
                    fragManager.beginTransaction().apply {
                        hide(coffeeHomeFragment)
                        hide(coffeeLikedFragment)
                        hide(coffeeCartFragment)
                        show(coffeeNotificationFragment)
                            .commit()
                    }
                    true
                }
                else -> {
                    true
                }
            }
            }
        }
        viewModel.selectedCoffee.observe(this, {
            val intent = Intent(this, CoffeeDetailActivity::class.java)
            val coffee = coffeeDetails[viewModel.selectedCategory.value ?: 0]
                .values
                .toTypedArray()[0][viewModel.selectedCoffee.value ?: 0]
            Log.i("categoryChanged", "sending coffee $coffee")
            intent.putExtra("selectedCoffee", coffee)
            intent.putExtra("categoryInd", viewModel.selectedCategory.value)
            intent.putExtra("coffeeInd", viewModel.selectedCoffee.value)
            startActivity(intent)
        })
    }
}