package com.example.domain.use_case

import com.example.domain.models.CurrentUserModel
import com.example.domain.models.MyResult
import com.example.domain.repository.UserRepository

class GetCurrentUserUseCase(
    private val userRepository: UserRepository
) {

    suspend fun execute(): MyResult<CurrentUserModel>{
        return userRepository.getCurrentUser()
    }

}