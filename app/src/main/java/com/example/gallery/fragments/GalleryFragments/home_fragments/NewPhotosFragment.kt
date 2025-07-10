package com.example.gallery.fragments.GalleryFragments.home_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.databinding.NewPhotosFragmentBinding
import com.example.gallery.MyOnItemClickListener
import com.example.gallery.fragments.GalleryFragments.home_fragments.adapters.NewPhotosRCAdapter
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItem
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItemType

class NewPhotosFragment : Fragment(), MyOnItemClickListener {

    private lateinit var binding: NewPhotosFragmentBinding
    private lateinit var adapter: NewPhotosRCAdapter
    private lateinit var navController: NavController

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

        navController = findNavController()

        adapter = NewPhotosRCAdapter(this)
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

    override fun onItemClick(item: PhotoItem) {

        val args = Bundle()

        args.putString("title", item.title)
        args.putString("description", item.description)
        args.putInt("image", item.image)
        if (item.type == PhotoItemType.New) args.putBoolean("new?", true) else args.putBoolean("new?", false)
        navController.navigate(R.id.action_homeFragment_to_detailFragment, args)
    }
}