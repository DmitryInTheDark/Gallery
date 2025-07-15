package com.example.domain.repository

import java.io.InputStream

interface PhotoRepository {

    fun getPhotos(page: Int, isNew: Boolean, isPopular: Boolean): List<InputStream>

}