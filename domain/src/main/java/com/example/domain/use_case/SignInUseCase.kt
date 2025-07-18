package com.example.domain.use_case

import com.example.domain.models.MyResult
import com.example.domain.models.SignInUserModel
import com.example.domain.repository.UserRepository

class SignInUseCase(private val userRepository: UserRepository) {

    suspend fun execute(signInUserModel: SignInUserModel): MyResult<String>{
        if (signInUserModel.email.isEmpty() && signInUserModel.password.isEmpty()) return  MyResult.Error("Пустые поля ввода")
        return userRepository.signIn(signInUserModel)
    }

}