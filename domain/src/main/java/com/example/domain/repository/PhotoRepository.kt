package com.example.domain.repository

import com.example.domain.models.MyResult
import com.example.domain.models.PhotoModel

interface PhotoRepository {

    suspend fun getPhotos(page: Int, isNew: Boolean, isPopular: Boolean): MyResult<List<PhotoModel>>

}