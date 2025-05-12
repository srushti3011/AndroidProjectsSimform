package com.example.learningproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class LifecycleFragment : Fragment() {

    val logtag = "observe"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(logtag, "LifecycleFragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(logtag, "LifecycleFragment onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment.
        Log.i(logtag, "LifecycleFragment onCreateView")
        return inflater.inflate(R.layout.fragment_lifecycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(logtag, "LifecycleFragment onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.i(logtag, "LifecycleFragment onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(logtag, "LifecycleFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(logtag, "LifecycleFragment onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(logtag, "LifecycleFragment onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(logtag, "LifecycleFragment onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(logtag, "LifecycleFragment onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(logtag, "LifecycleFragment onDetach")
    }
}