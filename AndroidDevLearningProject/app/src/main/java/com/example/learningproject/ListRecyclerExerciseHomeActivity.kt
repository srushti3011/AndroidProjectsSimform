package com.example.learningproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityListRecyclerExerciseHomeBinding

class ListRecyclerExerciseHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListRecyclerExerciseHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListRecyclerExerciseHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGoToListViewExercise.setOnClickListener {
            val intent = Intent(this, ListViewExercise::class.java)
            startActivity(intent)
        }

        binding.btnGoToRecyclerViewExercise.setOnClickListener {
            val intent = Intent(this, RecyclerViewExerciseActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoToRecyclerViewGridManager.setOnClickListener {
            val intent = Intent(this, RecyclerViewItemDecorationGridExercise::class.java)
            startActivity(intent)
        }

        binding.btnGoToTabViewPagerExercise.setOnClickListener {
            val intent = Intent(this, ViewPagerWithTabExerciseActivity::class.java)
            startActivity(intent)
        }
        binding.btnGoToBottomNavExercise.setOnClickListener {
            val intent = Intent(this, BottomNavigationExercise::class.java)
            startActivity(intent)
        }

        binding.btnGoToExpandableListViewExercise.setOnClickListener {
            val intent = Intent(this, ExpandableListViewExerciseActivity::class.java)
            startActivity(intent)
        }
    }
}