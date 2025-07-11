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

        binding.toolbarEditPassword.setupWithNavController(navController)

        binding.editTextOldPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.editTextNewPassword.text.isNotEmpty() && binding.editTextConfirmPassword.text.isNotEmpty()){
                    binding.buttonConfirmPasswordChange.isEnabled = true
                    binding.buttonConfirmPasswordChange.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.buttonConfirmPasswordChange.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.buttonConfirmPasswordChange.isEnabled = false
                    binding.buttonConfirmPasswordChange.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.buttonConfirmPasswordChange.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })
        binding.editTextNewPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.editTextOldPassword.text.isNotEmpty() && binding.editTextConfirmPassword.text.isNotEmpty()){
                    binding.buttonConfirmPasswordChange.isEnabled = true
                    binding.buttonConfirmPasswordChange.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.buttonConfirmPasswordChange.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.buttonConfirmPasswordChange.isEnabled = false
                    binding.buttonConfirmPasswordChange.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.buttonConfirmPasswordChange.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })
        binding.editTextConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.editTextOldPassword.text.isNotEmpty() && binding.editTextNewPassword.text.isNotEmpty()){
                    binding.buttonConfirmPasswordChange.isEnabled = true
                    binding.buttonConfirmPasswordChange.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.buttonConfirmPasswordChange.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.buttonConfirmPasswordChange.isEnabled = false
                    binding.buttonConfirmPasswordChange.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.buttonConfirmPasswordChange.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })

        binding.buttonConfirmPasswordChange.setOnClickListener {
            navController.navigate(R.id.action_editPasswordFragment_to_congratulationFragment)
        }
    }
}