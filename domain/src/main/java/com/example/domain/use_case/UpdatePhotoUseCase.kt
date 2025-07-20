package com.example.domain.use_case

import com.example.domain.models.MyResult
import com.example.domain.models.SinglePhotoModel
import com.example.domain.repository.PhotoRepository

class UpdatePhotoUseCase(private val photoRepository: PhotoRepository) {

    suspend fun execute(name: String, description: String, isNew: Boolean, isPopular: Boolean, id: String): MyResult<SinglePhotoModel>{
        if(name.isEmpty() || description.isEmpty()) return MyResult.Error(SinglePhotoModel(
            null, "", "", false, false, "", ""
        ))else return photoRepository.updatePhotoByID(
            name,
            description,
            isNew,
            isPopular,
            id
        )
    }
}