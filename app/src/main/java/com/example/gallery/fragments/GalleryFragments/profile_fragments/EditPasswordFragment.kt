package com.example.gallery.fragments.GalleryFragments.profile_fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gallery.R
import com.example.gallery.databinding.EditPasswordFragmentBinding
import com.example.gallery.databinding.ProfileFragmentBinding
import com.example.gallery.databinding.SettingsFragmentBinding

class EditPasswordFragment : Fragment() {

    private lateinit var binding: EditPasswordFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditPasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.materialToolbar4.setupWithNavController(navController)

        binding.userNameEditText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.birthdayEditText2.text.isNotEmpty() && binding.phoneNumberEditText2.text.isNotEmpty()){
                    binding.button3.isEnabled = true
                    binding.button3.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.button3.isEnabled = false
                    binding.button3.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })
        binding.birthdayEditText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.userNameEditText2.text.isNotEmpty() && binding.phoneNumberEditText2.text.isNotEmpty()){
                    binding.button3.isEnabled = true
                    binding.button3.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.button3.isEnabled = false
                    binding.button3.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })
        binding.phoneNumberEditText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.userNameEditText2.text.isNotEmpty() && binding.birthdayEditText2.text.isNotEmpty()){
                    binding.button3.isEnabled = true
                    binding.button3.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.button3.isEnabled = false
                    binding.button3.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.button3.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })

        binding.button3.setOnClickListener {
            navController.navigate(R.id.action_editPasswordFragment_to_congratulationFragment)
        }
    }
}