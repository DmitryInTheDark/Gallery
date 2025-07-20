package com.example.domain.use_case

import com.example.domain.repository.UserRepository

class UpdateUserDataUseCase (
    private val userRepository: UserRepository
) {

    suspend fun execute(
        username: String,
        birthday: String,
        phone: String,
        email: String,
        id: String,
        oldPassword: String,
        newPassword: String
    ): Boolean{
        return userRepository.updateUser(
            username, birthday, phone, email, id, oldPassword, newPassword
        )
    }
}