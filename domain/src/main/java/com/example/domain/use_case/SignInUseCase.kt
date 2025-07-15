package com.example.domain.use_case

import com.example.domain.repository.UserRepository

class SignInUseCase(private val userRepository: UserRepository) {

    private fun execute(): String{
        return userRepository.signIn()
    }

}