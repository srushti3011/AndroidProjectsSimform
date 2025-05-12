package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityExpandableListViewExerciseBinding

class ExpandableListViewExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpandableListViewExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExpandableListViewExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val moviesForListCollection = arrayOf(
            MoviesForList(
                "Movie1",
                arrayOf("Line 1", "Line 2")
            ),
            MoviesForList(
                "Movie2",
                arrayOf("Line 1", "Line 2")
            ),
            MoviesForList(
                "Movie3",
                arrayOf("Line 1", "Line 2")
            ),
        )
        binding.expandableListView.setAdapter(MovieExpandableLVAdapter(
            this@ExpandableListViewExerciseActivity,
            moviesForListCollection
        ))
    }
}