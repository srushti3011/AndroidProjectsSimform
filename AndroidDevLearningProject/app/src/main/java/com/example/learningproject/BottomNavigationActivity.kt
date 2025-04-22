package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.learningproject.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replaceFragment(FirstFragment())
        binding.bottomNavigationView.menu.findItem(R.id.goToFragOne).isEnabled = false
        binding.bottomNavigationView.setOnItemSelectedListener { selectedItem ->
            when (selectedItem.itemId) {
                R.id.goToFragOne -> {
                    replaceFragment(FirstFragment())
                    true
                }
                R.id.goToFragTwo -> {
                    replaceFragment(SecondFragment())
                    true
                }
                R.id.goToFragThree -> {
                    replaceFragment(ThirdFragment())
                    true
                }
                else -> {
                    replaceFragment(LoginFragment())
                    true
                }
            }
        }
    }
    private fun replaceFragment( newFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, newFragment).commit()
    }
}