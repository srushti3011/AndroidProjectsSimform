package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningproject.databinding.ActivityRecyclerViewExerciseBinding

class RecyclerViewExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecyclerViewExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val data = listOf(
            RecyclerExerciseData(
                img = R.drawable.carrot,
                name = "Carrot"
            ),
            RecyclerExerciseData(
                img = R.drawable.whatsapp_camera,
                name = "Camera"
            ),
            RecyclerExerciseData(
                img = R.drawable.whatsapp_microphone,
                name = "Mic"
            )
        )
        binding.recyclerView.adapter = RecyclerViewExerciseAdapter(
            data
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}