package com.example.domain.repository

import com.example.domain.models.RegisterUserModel
import com.example.domain.models.MyResult

interface UserRepository {

    suspend fun signUp(registerUserModel: RegisterUserModel): MyResult<String>

    fun signIn(): MyResult<String>

}