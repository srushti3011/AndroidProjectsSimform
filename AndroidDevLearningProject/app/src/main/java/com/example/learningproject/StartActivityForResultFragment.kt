package com.example.learningproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.learningproject.databinding.FragmentStartActivityForResultBinding

class StartActivityForResultFragment(
    private val onClickListener: () -> Unit
) : Fragment() {

    private lateinit var binding: FragmentStartActivityForResultBinding
    val viewModel: FragmentStartActivityForResultVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartActivityForResultBinding.inflate(inflater, container, false)
        binding.btnStartActivity.setOnClickListener {
            onClickListener()
        }
        viewModel.result.observe(viewLifecycleOwner, {
            binding.tvComputedFullName.text = viewModel.result.value
        })
        return binding.root
    }
}