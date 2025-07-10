package com.example.gallery

import com.example.gallery.fragments.GalleryFragments.make_fragments.adapters.PhotoItem

interface MyOnItemClickListener {
    fun onItemClick(item: PhotoItem)
}