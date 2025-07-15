package com.example.domain.use_case

import com.example.domain.models.RegisterUserModel
import com.example.domain.models.Result
import com.example.domain.repository.UserRepository

class SignUpUseCase(private val userRepository: UserRepository) {

    suspend fun execute(registerUserModel: RegisterUserModel): Result<String>{

        if (registerUserModel.password != registerUserModel.confirmPassword){
            return Result.Error("Пароли не совпадают")
        }else if (registerUserModel.password.isEmpty() || registerUserModel.confirmPassword.isEmpty()){
            return Result.Error("Пустые поля паролей")
        }else {
            return userRepository.signUp(registerUserModel)
        }
    }
}