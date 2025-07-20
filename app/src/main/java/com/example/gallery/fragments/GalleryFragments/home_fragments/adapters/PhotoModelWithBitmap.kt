package com.example.gallery.fragments.GalleryFragments.home_fragments.adapters

import android.graphics.Bitmap
import java.io.InputStream

data class PhotoModelWithBitmap(
    val id: String,
    val path: String,
    val image: Bitmap,
    val isNew: Boolean,
    val isPopular: Boolean
)