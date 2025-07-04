package com.example.gallery.fragments.GalleryFragments.profile_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gallery.R
import com.example.gallery.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: SettingsFragmentBinding

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

        binding.toolbarSettings.setupWithNavController(navController)

        binding.buttonSaveSettings.setOnClickListener {
            navController.navigateUp()
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