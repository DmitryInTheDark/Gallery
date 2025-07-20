package com.example.gallery.fragments.GalleryFragments.home_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.databinding.HomeFragmentBinding
import com.example.gallery.databinding.PopularPhotosFragmentBinding
import com.example.gallery.fragments.GalleryFragments.home_fragments.adapters.HomeOnClickListener
import com.example.gallery.fragments.GalleryFragments.home_fragments.adapters.NewPhotosRCAdapter
import com.example.gallery.fragments.GalleryFragments.home_fragments.adapters.PhotoModelWithBitmap
import com.example.gallery.fragments.GalleryFragments.home_fragments.view_model.HomeViewModel
import com.example.gallery.view_pager_adapter.HomeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularPhotosFragment : Fragment(), HomeOnClickListener {

    private lateinit var binding: PopularPhotosFragmentBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PopularPhotosFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NewPhotosRCAdapter(this)
        binding.popularPhotosRCView.adapter = adapter
        binding.popularPhotosRCView.layoutManager = GridLayoutManager(requireContext(), 2)

        homeViewModel.popularPhotoLiveData.observe(viewLifecycleOwner){
            adapter.updatePhotoList(it)
            if (adapter.itemList.isNotEmpty()){
                binding.progressBar3.visibility = View.GONE
                binding.textView8.visibility = View.GONE
                binding.popularPhotosRCView.visibility = View.VISIBLE
            }
        }

        homeViewModel.getPopularPhoto()
    }

    override fun onClick(item: PhotoModelWithBitmap) {
        val args = Bundle()

        args.putString("id", item.id)

        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, args)
    }
}