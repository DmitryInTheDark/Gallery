package com.example.domain.use_case

import com.example.domain.repository.UserRepository

class DeleteUserUseCase(private val userRepository: UserRepository) {

    suspend fun execute(id: String): Boolean{
        return userRepository.deleteUser(id)
    }
}