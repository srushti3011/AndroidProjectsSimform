package com.example.learningproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learningproject.databinding.FragmentOneTransactionBinding

class OneTransactionFragment : Fragment() {

    private lateinit var binding: FragmentOneTransactionBinding
    private lateinit var fragment: TwoTransactionFragment

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("Transaction", "One onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Transaction", "One onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOneTransactionBinding.inflate(inflater, container, false)
        Log.i("Transaction", "One onCreateView")
        Log.i("Transaction", "$activity")
        configure()
        return binding.root
    }

    private fun configure() {
        val fragManager = activity?.supportFragmentManager
        binding.btnCarrot.setOnClickListener {
            if (fragManager != null) {
                if (fragManager.findFragmentByTag("TwoTransactionFragment") == null) {
                    fragment = TwoTransactionFragment()
                    val bundle = Bundle()
                    bundle.putInt("imgName", R.drawable.carrot)
                    fragment.arguments = bundle
                    val transaction = fragManager.beginTransaction()
                    transaction
                        .replace(R.id.fragContainer, fragment)
                        .addToBackStack("ShowImage")
                    transaction.commit()
                }
            }
        }
        binding.btnLocation.setOnClickListener {
            if (fragManager != null) {
                if (fragManager.findFragmentByTag("TwoTransactionFragment") == null) {
                    fragment = TwoTransactionFragment()
                    val bundle = Bundle()
                    bundle.putInt("imgName", R.drawable.location_icon)
                    fragment.arguments = bundle
                    val transaction = fragManager.beginTransaction()
                    transaction
                        .replace(R.id.fragContainer, fragment)
                        .addToBackStack("ShowImage")
                    transaction.commit()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Transaction", "One onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i("Transaction", "One onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Transaction", "One onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Transaction", "One onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Transaction", "One onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("Transaction", "One onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Transaction", "One onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i("Transaction", "One onDetach")
    }
}