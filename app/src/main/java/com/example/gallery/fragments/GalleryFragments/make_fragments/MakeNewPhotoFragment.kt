package com.example.gallery.fragments.GalleryFragments.make_fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.databinding.AlertExitFromCreateNewPhotoDialogBinding
import com.example.gallery.databinding.MakeFragmentBinding
import com.example.gallery.databinding.MakeNewPhotoFragmentBinding
import com.example.gallery.databinding.NewPhotosFragmentBinding
import com.example.gallery.fragments.GalleryFragments.recycler_adapters.MakeRCAdapter
import com.example.gallery.fragments.GalleryFragments.recycler_adapters.MyOnItemClickListener
import com.example.gallery.fragments.GalleryFragments.recycler_adapters.NewPhotosRCAdapter

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

        binding.editTextText3.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.editTextText4.text.isNotEmpty()){
                    binding.button2.isEnabled = true
                    binding.button2.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.button2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.button2.isEnabled = false
                    binding.button2.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.button2.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })
        binding.editTextText4.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s == null) return
                if (s.isNotEmpty() && binding.editTextText3.text.isNotEmpty()){
                    binding.button2.isEnabled = true
                    binding.button2.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.button2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                }else {
                    binding.button2.isEnabled = false
                    binding.button2.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray_light))
                    binding.button2.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                return
            }
        })

        binding.button2.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun showExitDialog(){

        val dialogView = layoutInflater.inflate(R.layout.alert_exit_from_create_new_photo_dialog, null)



        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .show()

        dialogView.findViewById<TextView>(R.id.textView22).setOnClickListener {
            alertDialog.dismiss()
            findNavController().popBackStack()
        }
        dialogView.findViewById<TextView>(R.id.textView24).setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
