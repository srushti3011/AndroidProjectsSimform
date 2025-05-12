package com.example.learningproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.learningproject.databinding.FragmentShowImageBinding

class ShowImageFragment : Fragment() {

    private lateinit var binding: FragmentShowImageBinding
    val sharedVM: ButtonImageSharedVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowImageBinding.inflate(inflater, container, false)

        sharedVM.selectedButton.observe(viewLifecycleOwner, {
            when (sharedVM.selectedButton.value) {
                SelectedButton.CALL -> {
                    binding.imgView.setImageResource(R.drawable.baseline_call_24)
                }
                SelectedButton.CARROT -> {
                    binding.imgView.setImageResource(R.drawable.carrot)
                }
                null -> {
                    binding.imgView.setImageResource(R.drawable.eye_open)
                }
            }
        })
        return binding.root
    }
}