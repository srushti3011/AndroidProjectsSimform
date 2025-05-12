package com.example.learningproject

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.learningproject.databinding.ActivityBottomNavigationExerciseBinding

class BottomNavigationExercise : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBottomNavigationExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fragmentList = listOf(
            HomeFragment(),
            SearchFragment(),
            MyProfileFragment()
        )
        binding.viewPager.adapter = ViewPagerTabExerciseAdapter(
            supportFragmentManager,
            lifecycle,
            fragmentList
        )
        binding.bottomNavigationView.setOnItemSelectedListener { selectedItem ->
            when (selectedItem.itemId) {
                R.id.home -> {
                    binding.viewPager.currentItem = 0
                    true
                }
                R.id.search -> {
                    binding.viewPager.currentItem = 1
                    true
                }
                R.id.profile -> {
                    binding.viewPager.currentItem = 2
                    true
                }
                else -> {
                    false
                }
            }
        }
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> binding.bottomNavigationView.selectedItemId = R.id.home
                        1 -> binding.bottomNavigationView.selectedItemId = R.id.search
                        2 -> binding.bottomNavigationView.selectedItemId = R.id.profile
                    }
                }
            }
        )
    }
}