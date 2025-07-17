package com.example.domain.use_case

import com.example.domain.models.MyResult
import com.example.domain.repository.UserRepository

class SignInUseCase(private val userRepository: UserRepository) {

    private fun execute(): MyResult<String>{
        return userRepository.signIn()
    }

}