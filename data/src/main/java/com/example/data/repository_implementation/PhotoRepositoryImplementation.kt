package com.example.data.repository_implementation

import android.graphics.BitmapFactory
import com.example.domain.repository.PhotoRepository
import java.io.InputStream


class PhotoRepositoryImplementation: PhotoRepository {
    override fun getPhotos(
        page: Int,
        isNew: Boolean,
        isPopular: Boolean
    ): List<InputStream> {
        TODO("Not yet implemented")
    }
}