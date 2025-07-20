package com.example.gallery.fragments.GalleryFragments.profile_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.databinding.ProfileFragmentBinding
import com.example.gallery.fragments.GalleryFragments.profile_fragments.adapters.ProfileRCAdapter
import com.example.gallery.fragments.GalleryFragments.profile_fragments.view_model.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: ProfileFragmentBinding

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.profileRCView.adapter = ProfileRCAdapter()
        binding.profileRCView.layoutManager = GridLayoutManager(requireContext(), 4)

        binding.imageButton.setOnClickListener{
            navController.navigate(R.id.action_profileFragment_to_settingsFragment)
        }

        profileViewModel.currentUserLiveData.observe(viewLifecycleOwner){
            binding.textProfileBirthday.text = it.birthday
            binding.textProfileUsername.text = it.displayName
        }

    }
}