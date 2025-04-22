package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learningproject.databinding.ActivityRecyclerViewItemDecorationGridExerciseBinding

class RecyclerViewItemDecorationGridExercise : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewItemDecorationGridExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecyclerViewItemDecorationGridExerciseBinding.inflate(layoutInflater)
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
                img = R.drawable.baseline_call_24,
                name = "Phone"
            ),
            RecyclerExerciseData(
                img = R.drawable.whatsapp_camera,
                name = "Camera"
            ),
            RecyclerExerciseData(
                img = R.drawable.carrot,
                name = "Carrot"
            ),
            RecyclerExerciseData(
                img = R.drawable.whatsapp_camera,
                name = "Camera"
            ),
            RecyclerExerciseData(
                img = R.drawable.whatsapp_camera,
                name = "Camera"
            ),
            RecyclerExerciseData(
                img = R.drawable.baseline_call_24,
                name = "Phone"
            ),
            RecyclerExerciseData(
                img = R.drawable.whatsapp_camera,
                name = "Camera"
            )
        )
        binding.recyclerView.adapter = RecyclerViewGridExerciseAdapter(
            data
        )
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.addItemDecoration(CustomMarginDecoration())
    }
}