package com.example.domain.use_case

import com.example.domain.repository.PhotoRepository
import sun.font.GlyphRenderData.Bitmap
import java.io.InputStream

class GetPhotosUseCase(private val photoRepository: PhotoRepository) {

    private fun execute(page: Int, isNew: Boolean, isPopular: Boolean): List<InputStream>{
        return photoRepository.getPhotos(page, isNew, isPopular)
    }
}