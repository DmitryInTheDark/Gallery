package com.example.domain.use_case

import com.example.domain.models.MyResult
import com.example.domain.models.SinglePhotoModel
import com.example.domain.repository.PhotoRepository

class GetPhotoByIDUseCase(private val photoRepository: PhotoRepository) {

    suspend fun execute(id: String): MyResult<SinglePhotoModel>{
        return photoRepository.getPhotoByID(id)
    }
}