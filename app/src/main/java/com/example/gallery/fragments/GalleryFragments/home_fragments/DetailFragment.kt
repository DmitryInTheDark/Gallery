package com.example.gallery.fragments.GalleryFragments.home_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.gallery.R
import com.example.gallery.databinding.DetailsFragmentBinding

class DetailFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private var isEdit = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.materialToolbar5.setupWithNavController(navController)
        binding.materialToolbar5.title = null

        val args = arguments

        binding.imageView6.setImageResource(args?.getInt("image") ?: R.drawable.test_photo)
        binding.textImageName.text = args?.getString("title") ?: "Default title"
        binding.textImageDescription.text = args?.getString("description") ?: "Default description"

        binding.imageView6.setImageResource(args?.getInt("image") ?: R.drawable.test_photo)
        binding.editTextImageName.setText(args?.getString("title") ?: "Default title")
        binding.editTextImageDescription.setText(args?.getString("description") ?: "Default description")

        val isNew = args?.getBoolean("new?") ?: true
        if (isNew) binding.radioButtonNew.isChecked else binding.radioButtonPopular.isChecked

        Log.i("my", args?.getString("title") ?: "Ничего не пришло")
        Log.i("my", args?.getString("description") ?: "Ничего не пришло")
        Log.i("my", isNew.toString())

         binding.buttonConfirmDetailsChanges.setOnClickListener{
             binding.materialToolbar5.menu.add("Edit")
             binding.detailLayout.visibility = View.VISIBLE
             binding.editLayout.visibility = View.GONE
             binding.materialToolbar5.title = null
         }

        binding.materialToolbar5.setOnMenuItemClickListener(object : OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                    binding.detailLayout.visibility = View.GONE
                    binding.editLayout.visibility = View.VISIBLE
                    binding.materialToolbar5.title = "Edit Photo"
                    binding.materialToolbar5.menu.clear()
                return true
            }
        })
    }
}