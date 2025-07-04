package com.example.gallery.fragments.GalleryFragments.home_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.databinding.NewPhotosFragmentBinding
import com.example.gallery.fragments.GalleryFragments.recycler_adapters.NewPhotosRCAdapter

class NewPhotosFragment : Fragment() {

    private lateinit var binding: NewPhotosFragmentBinding
    private lateinit var adapter: NewPhotosRCAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewPhotosFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        adapter = NewPhotosRCAdapter(navController)
        binding.newPhotosRCView.adapter = adapter
        binding.newPhotosRCView.layoutManager = GridLayoutManager(requireContext(), 2)



        binding.progressBar.setOnClickListener {
            binding.errorLayout.visibility = View.VISIBLE
            binding.progressLayout.visibility = View.GONE
        }
        binding.errorLayout.setOnClickListener {
            binding.errorLayout.visibility = View.GONE
            binding.newPhotosRCView.visibility = View.VISIBLE
        }
    }
}