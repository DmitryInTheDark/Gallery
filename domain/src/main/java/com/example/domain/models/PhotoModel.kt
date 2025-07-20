package com.example.domain.models

import java.io.InputStream

data class PhotoModel(
    val id: Int,
    val path: String,
    val image: InputStream,
    val isNew: Boolean,
    val isPopular: Boolean
)