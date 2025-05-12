package com.example.learningproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learningproject.databinding.FragmentTwoTransactionBinding

class TwoTransactionFragment : Fragment() {

    private lateinit var binding: FragmentTwoTransactionBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("Transaction", "Two onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Transaction", "Two onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoTransactionBinding.inflate(inflater, container, false)
        Log.i("Transaction", "Two onCreateView")
        val args = arguments
        val img = args?.getInt("imgName")
        binding.imgReceivedImage.setImageResource(img ?: R.drawable.emoji_smile)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Transaction", "Two onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Transaction", "Two onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Transaction", "Two onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Transaction", "Two onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Transaction", "Two onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("Transaction", "Two onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Transaction", "Two onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("Transaction", "Two onDetach")
    }
}