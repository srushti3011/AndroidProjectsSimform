package com.example.learningproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learningproject.databinding.FragmentStudentDataInputBinding

class StudentDataInputFragment : Fragment() {

    private lateinit var binding: FragmentStudentDataInputBinding
    private lateinit var communicator: DataAndResultCommunicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDataInputBinding.inflate(inflater, container, false)
        communicator = activity as DataAndResultCommunicator
        binding.btnShowResult.setOnClickListener {
            communicator.passIDNum(binding.etIDNumber.text.toString().toInt())
        }
        return binding.root
    }
}