package com.example.learningproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.learningproject.databinding.FragmentSelectionBinding

class SelectionFragment : Fragment() {

    private lateinit var binding: FragmentSelectionBinding
    val sharedVM: ButtonImageSharedVM by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectionBinding.inflate(inflater, container, false)

        binding.btnShowCarrot.setOnClickListener {
            sharedVM.setSelectedButton(SelectedButton.CARROT)
        }

        binding.btnShowCall.setOnClickListener {
            sharedVM.setSelectedButton(SelectedButton.CALL)
        }
        return binding.root
    }
}

enum class SelectedButton {
    CARROT, CALL
}