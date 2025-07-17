package com.example.domain.use_case

import com.example.domain.models.RegisterUserModel
import com.example.domain.models.Result
import com.example.domain.repository.UserRepository

class SignUpUseCase(private val userRepository: UserRepository) {

    suspend fun execute(registerUserModel: RegisterUserModel): Result<String>{

        return if (registerUserModel.password != registerUserModel.confirmPassword){
            Result.Error("Пароли не совпадают")
        }else if (registerUserModel.password.isEmpty() || registerUserModel.confirmPassword.isEmpty()){
            Result.Error("Пустые поля паролей")
        }else {
            userRepository.signUp(registerUserModel)
        }
    }
}