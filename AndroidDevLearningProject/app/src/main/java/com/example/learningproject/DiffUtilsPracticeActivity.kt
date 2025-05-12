package com.example.learningproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningproject.databinding.ActivityDiffUtilsPracticeBinding

class DiffUtilsPracticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiffUtilsPracticeBinding
    var count = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDiffUtilsPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvDiffUtil.layoutManager = LinearLayoutManager(this)
        val adapter = DiffUtilAdaperClass(personData)
        binding.rvDiffUtil.adapter = adapter

        binding.btnAddData.setOnClickListener {
            val personToBeAdded = PersonItem(
                id = ++count,
                name = "person $count",
            )
            val newList = personData + personToBeAdded
            adapter.updateData(newList)
        }

        binding.btnChangeAttribute.setOnClickListener {
            val updatedPersonData = personData.mapIndexed { index, person ->
                if (index == 0) {
                    person.copy(name = "Updated Person 1")
                } else {
                    person
                }
            }
            adapter.updateData(updatedPersonData)
        }
    }
}