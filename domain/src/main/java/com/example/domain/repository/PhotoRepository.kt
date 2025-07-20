package com.example.domain.repository

import com.example.domain.models.MyResult
import com.example.domain.models.PhotoModel
import com.example.domain.models.SinglePhotoModel

interface PhotoRepository {

    suspend fun getPhotos(page: Int, isNew: Boolean, isPopular: Boolean): MyResult<List<PhotoModel>>

    suspend fun getPhotoByID(id: String): MyResult<SinglePhotoModel>

    suspend fun updatePhotoByID(
        name: String,
        description: String,
        isNew: Boolean,
        isPopular: Boolean,
        id: String
    ): MyResult<SinglePhotoModel>

}