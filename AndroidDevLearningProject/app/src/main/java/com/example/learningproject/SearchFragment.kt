package com.example.learningproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learningproject.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movies = listOf(
            Movies(
                name = "Movie1",
                description = "Movie1 description"
            ),
            Movies(
                name = "Movie2",
                description = "Movie2 description"
            ),
            Movies(
                name = "Movie3",
                description = "Movie3 description"
            ),
            Movies(
                name = "Movie4",
                description = "Movie4 description"
            ),
        )
        binding.rvMovies.adapter = MovieExpandableRVAdapter(
            movies
        )
        binding.rvMovies.layoutManager = LinearLayoutManager(binding.rvMovies.context)
    }
}