package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityViewPagerWithTabExerciseBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerWithTabExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerWithTabExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewPagerWithTabExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fragmentList = listOf(
            RecentsFragment(),
            MyFavoritesFragment()
        )
        val adapter = ViewPagerTabExerciseAdapter(
            supportFragmentManager,
            lifecycle,
            fragmentList
        )
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, index ->
            when(index) {
                0 -> tab.text = "Recent"
                1 -> tab.text = "My Favorite"
            }
        }.attach()
    }
}