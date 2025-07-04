package com.example.gallery.fragments.registration_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gallery.R
import com.example.gallery.databinding.SignUpFragmentBinding

class SignUpFragment : Fragment() {

    lateinit var binding: SignUpFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.buttonBack.setOnClickListener {
            navController.navigateUp()
        }

        binding.signInButton.setOnClickListener{
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.signUpButton.setOnClickListener{
            navController.navigate(R.id.action_signUpFragment_to_mainFragment)
        }
    }
}