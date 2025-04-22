package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityListViewExerciseBinding

class ListViewExercise : AppCompatActivity() {

    private lateinit var binding: ActivityListViewExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListViewExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listExerciseDataCollection = listOf(
            ListExerciseData(
                img = R.drawable.carrot,
                title = "Carrot",
                subtitle = "Carrot Subtitle"
            ),
            ListExerciseData(
                img = R.drawable.whatsapp_camera,
                title = "Camera",
                subtitle = "Camera Subtitle"
            ),
            ListExerciseData(
                img = R.drawable.baseline_account_circle_24,
                title = "Account",
                subtitle = "Account Subtitle"
            ),
            ListExerciseData(
                img = R.drawable.baseline_call_24,
                title = "Phone call",
                subtitle = "Phone call Subtitle"
            ),
            ListExerciseData(
                img = R.drawable.whatsapp_video,
                title = "Video Call",
                subtitle = "Video Call Subtitle"
            )
        )
        binding.listView.adapter = ListViewExerciseAdapter(
            this,
            listExerciseDataCollection
        )
    }
}