package com.example.learningproject

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.learningproject.databinding.ActivityListViewPracticeBinding

class ListViewPracticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListViewPracticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListViewPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpSimpleList()
        setUpCustomAdapterList()
    }
    private fun setUpSimpleList() {
        val fruits = arrayOf("Mango", "Orange", "Apple", "Kiwi", "Banana")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fruits)
        binding.listViewFruits.adapter = adapter
        binding.listViewFruits.setOnItemClickListener { adapterView, view, index, l ->
            val selectedItem = adapterView.getItemAtPosition(index)
            Toast.makeText(this, "Selected $selectedItem", Toast.LENGTH_SHORT).show()
            Log.i("ListViewItemSelected", "$index $l $adapterView")
        }
    }

    private fun setUpCustomAdapterList() {
        val userList = listOf(
            User(
                name = "User One",
                age = 23,
                city = "Mumbai",
                occupation = "HR",
                img = R.drawable.eye_open
            ),
            User(
                name = "User Two",
                age = 25,
                city = "Ahmedabad",
                occupation = "Software engineer",
                img = R.drawable.eye_open
            ),
            User(
                name = "User Three",
                age = 30,
                city = "Surat",
                occupation = "Electrical engineer",
                img = R.drawable.eye_open
            ),
            User(
                name = "User Four",
                age = 20,
                city = "Kochi",
                occupation = "Software engineer",
                img = R.drawable.eye_open
            )
        )
        binding.listViewUsers.adapter = UserListAdapter(this, userList)
    }
}