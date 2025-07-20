package com.example.gallery.view_pager_adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gallery.fragments.GalleryFragments.home_fragments.NewPhotosFragment
import com.example.gallery.fragments.GalleryFragments.home_fragments.PopularPhotosFragment

class HomeViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    val fragmentList: List<Fragment> = listOf(NewPhotosFragment(), PopularPhotosFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}