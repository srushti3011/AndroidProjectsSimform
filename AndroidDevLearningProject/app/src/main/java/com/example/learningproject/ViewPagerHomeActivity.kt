package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityViewPagerHomeBinding

class ViewPagerHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityViewPagerHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGoToFragmentViewPager.setOnClickListener {
            val intent = Intent(this, FragmentViewPagerActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToViewPagerWithTabLayout.setOnClickListener {
            val intent = Intent(this, ViewPagerTabLayoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToViewPagerRecyclerViewAdapter.setOnClickListener {
            val intent = Intent(this, ViewPagerRecyclerAdapterActivity::class.java)
            startActivity(intent)
        }
    }
}