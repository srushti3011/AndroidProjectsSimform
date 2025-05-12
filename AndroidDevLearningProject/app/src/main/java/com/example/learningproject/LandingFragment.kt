package com.example.learningproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.learningproject.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {

    private lateinit var binding: FragmentLandingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLandingBinding.inflate(inflater, container, false)

        val toLogin = LandingFragmentDirections.actionLandingFragmentToLoginFragment()
        binding.btnLogin.setOnClickListener {
            it.findNavController().navigate(toLogin)
        }

        val toSignup = LandingFragmentDirections.actionLandingFragmentToSignupFragment()
        binding.btnSignup.setOnClickListener {
//            it.findNavController().navigate(R.id.action_landingFragment_to_signupFragment)
            it.findNavController().navigate(toSignup)
        }
        return binding.root
    }

}