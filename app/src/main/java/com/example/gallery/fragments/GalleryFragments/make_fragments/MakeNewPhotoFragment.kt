package com.example.gallery.fragments.GalleryFragments.make_fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gallery.R
import com.example.gallery.databinding.MakeNewPhotoFragmentBinding

class MakeNewPhotoFragment : Fragment() {

    private lateinit var binding: MakeNewPhotoFragmentBinding
    
    private val backCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            showExitDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MakeNewPhotoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.materialToolbar3.setupWithNavController(navController)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backCallback)

        binding.materialToolbar3.setNavigationOnClickListener {
            showExitDialog()
        }

        binding.materialToolbar3.title = "New Photo"

        binding.editNameNewPhoto.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.editDescriptionNewPhoto.text.isNotEmpty()){
                    binding.buttonAddNewPhoto.isEnabled = true
                    binding.buttonAddNewPhoto.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.buttonAddNewPhoto.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.buttonAddNewPhoto.isEnabled = false
                    binding.buttonAddNewPhoto.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.buttonAddNewPhoto.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })
        binding.editDescriptionNewPhoto.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.editNameNewPhoto.text.isNotEmpty()){
                    binding.buttonAddNewPhoto.isEnabled = true
                    binding.buttonAddNewPhoto.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.buttonAddNewPhoto.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.buttonAddNewPhoto.isEnabled = false
                    binding.buttonAddNewPhoto.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.buttonAddNewPhoto.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })

        binding.buttonAddNewPhoto.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun showExitDialog(){

        val dialogView = layoutInflater.inflate(R.layout.alert_exit_from_create_new_photo_dialog, null)



        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .show()

        dialogView.findViewById<TextView>(R.id.alert_exit_new_photo_text).setOnClickListener {
            alertDialog.dismiss()
            findNavController().popBackStack()
        }
        dialogView.findViewById<TextView>(R.id.alert_exitNew_photo_cancel_text).setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
