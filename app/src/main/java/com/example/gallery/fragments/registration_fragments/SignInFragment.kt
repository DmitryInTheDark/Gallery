package com.example.gallery.fragments.registration_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gallery.R
import com.example.gallery.databinding.SignInFragmentBinding

class SignInFragment : Fragment() {

    lateinit var binding: SignInFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignInFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.backSignInButton.setOnClickListener {
            navController.navigateUp()
        }

        binding.SignInButton.setOnClickListener{
            navController.navigate(R.id.action_signInFragment_to_mainFragment)
        }
        binding.SignUpButton.setOnClickListener{
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}