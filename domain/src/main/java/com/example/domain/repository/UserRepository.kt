package com.example.domain.repository

import com.example.domain.models.RegisterUserModel
import com.example.domain.models.MyResult
import com.example.domain.models.SignInUserModel

interface UserRepository {

    suspend fun signUp(registerUserModel: RegisterUserModel): MyResult<String>

    suspend fun signIn(signInUserModel: SignInUserModel): MyResult<String>

}