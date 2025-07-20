package com.example.domain.models

import java.io.InputStream

data class SinglePhotoModel(
    val inputStream: InputStream?,
    val description: String,
    val name: String,
    val isNew: Boolean,
    val isPopular: Boolean,
    val author: String,
    val dateCreate: String
)