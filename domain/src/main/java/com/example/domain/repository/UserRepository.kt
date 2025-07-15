package com.example.domain.repository

import com.example.domain.models.RegisterUserModel
import com.example.domain.models.Result

interface UserRepository {

    suspend fun signUp(registerUserModel: RegisterUserModel): Result<String>

    fun signIn(): String

}