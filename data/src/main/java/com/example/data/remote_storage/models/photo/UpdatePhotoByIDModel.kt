package com.example.data.remote_storage.models.photo

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePhotoByIDModel(
    val path: String,
    
)