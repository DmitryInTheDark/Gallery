package com.example.domain.use_case

import com.example.domain.models.MyResult
import com.example.domain.models.PhotoModel
import com.example.domain.repository.PhotoRepository
import sun.font.GlyphRenderData.Bitmap
import java.io.InputStream

class GetPhotosUseCase(private val photoRepository: PhotoRepository) {

    suspend fun execute(page: Int, isNew: Boolean, isPopular: Boolean): MyResult<List<PhotoModel>>{
        return photoRepository.getPhotos(page, isNew, isPopular)
    }
}