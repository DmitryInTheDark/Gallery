package com.example.gallery.fragments.GalleryFragments.make_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.databinding.MakeFragmentBinding
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.MakeRCAdapter
import com.example.gallery.MyOnItemClickListener
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItem

class MakeFragment : Fragment(), MyOnItemClickListener {

    private lateinit var binding: MakeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MakeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.makeRCView.adapter = MakeRCAdapter(this)
        binding.makeRCView.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.materialToolbar.title = "All Photos"
    }

    override fun onItemClick(item: PhotoItem) {
        binding.imageMake.setImageResource(item.image)
    }
}