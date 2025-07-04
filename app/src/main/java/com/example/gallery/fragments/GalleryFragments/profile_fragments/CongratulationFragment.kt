package com.example.gallery.fragments.GalleryFragments.profile_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gallery.R
import com.example.gallery.databinding.CongratulationFragmentBinding

class CongratulationFragment : Fragment() {

    private lateinit var binding: CongratulationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = CongratulationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.buttonConfirm.setOnClickListener {
            navController.navigate(R.id.action_congratulationFragment_to_settingsFragment)
        }
    }
}