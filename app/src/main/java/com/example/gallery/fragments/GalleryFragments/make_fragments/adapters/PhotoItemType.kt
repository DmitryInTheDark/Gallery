package com.example.gallery.fragments.GalleryFragments.make_fragments.adapters

sealed class PhotoItemType {

    data object New: PhotoItemType()

    data object Popular: PhotoItemType()
}