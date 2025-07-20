package com.example.gallery.fragments.GalleryFragments.home_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gallery.databinding.HomeFragmentBinding
import com.example.gallery.view_pager_adapter.HomeViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleList = listOf("New", "Popular")
        binding.homeViewPager.adapter = HomeViewPagerAdapter(this)

        TabLayoutMediator(binding.homeTabLayout, binding.homeViewPager){ tab, pos ->
            tab.text = titleList[pos]
        }.attach()

    }
}