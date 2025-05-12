package com.example.learningproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learningproject.databinding.FragmentShowResultBinding

class ShowResultFragment : Fragment() {

    private lateinit var binding: FragmentShowResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun updateUI() {
        val textToSet = "ID No.: ${arguments?.getInt("idNum")} Score: ${arguments?.getInt("marks")}"
        binding.showResult.text = textToSet
    }
}