package com.example.domain.use_case

import com.example.domain.models.RegisterUserModel
import com.example.domain.models.MyResult
import com.example.domain.repository.UserRepository

class SignUpUseCase(private val userRepository: UserRepository) {

    suspend fun execute(registerUserModel: RegisterUserModel): MyResult<String>{

        return if (registerUserModel.password != registerUserModel.confirmPassword){
            MyResult.Error("Пароли не совпадают")
        }else if (registerUserModel.password.isEmpty() || registerUserModel.confirmPassword.isEmpty()){
            MyResult.Error("Пустые поля паролей")
        }else {
            userRepository.signUp(registerUserModel)
        }
    }
}