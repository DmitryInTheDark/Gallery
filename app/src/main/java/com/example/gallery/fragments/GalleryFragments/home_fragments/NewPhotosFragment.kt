package com.example.gallery.fragments.GalleryFragments.home_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.models.PhotoModel
import com.example.gallery.R
import com.example.gallery.databinding.NewPhotosFragmentBinding
import com.example.gallery.fragments.GalleryFragments.MyOnItemClickListener
import com.example.gallery.fragments.GalleryFragments.home_fragments.adapters.HomeOnClickListener
import com.example.gallery.fragments.GalleryFragments.home_fragments.adapters.NewPhotosRCAdapter
import com.example.gallery.fragments.GalleryFragments.home_fragments.view_model.HomeViewModel
import com.example.gallery.fragments.GalleryFragments.home_fragments.view_model.HomeViewModelFactory
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItem
import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItemType

class NewPhotosFragment : Fragment(), HomeOnClickListener {

    private lateinit var binding: NewPhotosFragmentBinding
    private lateinit var adapter: NewPhotosRCAdapter
    private lateinit var navController: NavController
    private lateinit var homeViewModel: HomeViewModel

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

        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(requireContext()))[HomeViewModel::class.java]

        adapter = NewPhotosRCAdapter(this)
        binding.newPhotosRCView.adapter = adapter
        binding.newPhotosRCView.layoutManager = GridLayoutManager(requireContext(), 2)

        homeViewModel.newPhotoLiveData.observe(viewLifecycleOwner){
            adapter.updatePhotoList(it)
            if (adapter.itemList.isNotEmpty()){
                binding.progressLayout.visibility = View.GONE
                binding.newPhotosRCView.visibility = View.VISIBLE
            }
        }


    }

    override fun onClick(item: PhotoModel) {
        Toast.makeText(requireContext(), item.id.toString(), Toast.LENGTH_SHORT).show()
    }
}