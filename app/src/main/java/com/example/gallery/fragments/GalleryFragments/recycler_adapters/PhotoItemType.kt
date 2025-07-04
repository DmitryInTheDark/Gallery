package com.example.gallery.fragments.GalleryFragments.recycler_adapters

sealed class PhotoItemType {

    data object New: PhotoItemType()

    data object Popular: PhotoItemType()
}