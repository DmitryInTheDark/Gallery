package com.example.gallery.fragments.GalleryFragments.profile_fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.data.remote_storage.RetrofitClient
import com.example.data.repository_implementation.UserRepositoryImplementation
import com.example.gallery.R
import com.example.gallery.databinding.SettingsFragmentBinding
import com.example.gallery.fragments.GalleryFragments.profile_fragments.view_model.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: SettingsFragmentBinding

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val sharedPreferences = context?.applicationContext?.getSharedPreferences(RetrofitClient.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val password = sharedPreferences!!.getString(UserRepositoryImplementation.PASSWORD, "")!!


        profileViewModel.currentUserLiveData.observe(viewLifecycleOwner){
            binding.userNameEditTextSettings.setText(it.displayName)
            binding.birthdayEditTextSettings.setText(it.birthday)
            binding.phoneNumberEditTextSettings.setText(it.phone)
            binding.emailEditTextSettings.setText(it.email)
            Toast.makeText(requireContext(), "Обновление данных успешно", Toast.LENGTH_SHORT).show()
        }

        binding.toolbarSettings.setupWithNavController(navController)

        binding.buttonSaveSettings.setOnClickListener {
            profileViewModel.updateUser(
                binding.userNameEditTextSettings.text.toString(),
                binding.birthdayEditTextSettings.text.toString(),
                binding.phoneNumberEditTextSettings.text.toString(),
                binding.emailEditTextSettings.text.toString(),
                password,
                password,
                profileViewModel.currentUserLiveData.value?.id.toString()
            )
        }

        binding.textResetPassword.setOnClickListener {
            navController.navigate(R.id.action_settingsFragment_to_editPasswordFragment)
        }

        binding.textDeleteAccount.setOnClickListener {
            showDeleteDialog()
        }
        binding.textSignOut.setOnClickListener {
            showExitDialog()
        }
    }

    private fun showExitDialog(){
        val dialog = layoutInflater.inflate(R.layout.alert_sign_out_dialog, null)

        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialog)
            .show()

        dialog.findViewById<TextView>(R.id.alert_sign_out_text).setOnClickListener {
            alertDialog.dismiss()
            requireActivity().finish()
        }

        dialog.findViewById<TextView>(R.id.alert_sign_out_cancel_text).setOnClickListener {
            alertDialog.dismiss()
        }

    }
    private fun showDeleteDialog(){
        val dialog = layoutInflater.inflate(R.layout.alert_delete_account_dialog, null)

        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialog)
            .show()

        dialog.findViewById<TextView>(R.id.alert_delete_text).setOnClickListener {
            alertDialog.dismiss()
            requireActivity().finish()
        }

        dialog.findViewById<TextView>(R.id.alert_delete_cancel_text).setOnClickListener {
            alertDialog.dismiss()
        }

    }
}