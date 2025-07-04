package com.example.gallery.fragments.GalleryFragments.recycler_adapters

data class PhotoItem(
    val image: Int,
    val title: String,
    val description: String,
    val type: PhotoItemType
)