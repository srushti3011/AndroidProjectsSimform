package com.example.learningproject

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.learningproject.databinding.ActivityFragmentViewPagerBinding

class FragmentViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFragmentViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fragmentList = listOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        val adapter = ViewPagerAdapter(
            supportFragmentManager,
            lifecycle,
            fragmentList
        )
        binding.viewPager.adapter = adapter
        binding.viewPager.currentItem = 1

        binding.btnBack.setOnClickListener {
            changeFragment(-1)
        }
        binding.btnNext.setOnClickListener {
            changeFragment(+1)
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.btnBack.visibility = View.GONE
                } else {
                    binding.btnBack.visibility = View.VISIBLE
                }

                if (position == fragmentList.size - 1) {
                    binding.btnNext.visibility = View.GONE
                } else {
                    binding.btnNext.visibility = View.VISIBLE
                }
            }
        })
    }

    fun changeFragment(value: Int) {
        val newItem = binding.viewPager.currentItem + value
        binding.viewPager.currentItem = newItem
    }
}