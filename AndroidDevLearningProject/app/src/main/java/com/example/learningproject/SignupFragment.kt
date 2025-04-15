package com.example.learningproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs

class SignupFragment : Fragment() {

//    val message: SignupFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        Toast.makeText(context, message.data?.toString(), Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.fragment_signup, container, false)

    }


}