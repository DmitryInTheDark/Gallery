package com.example.data.remote_storage

import com.example.data.remote_storage.models.token.GetTokenBody
import com.example.data.remote_storage.models.register_user.RegisterUserBody
import com.example.data.remote_storage.models.register_user.RegisterUserResponse
import com.example.data.remote_storage.models.token.GetTokenResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("users")
    suspend fun registerUser(
        @Body registerUserBody: RegisterUserBody
    ): Response<RegisterUserResponse>

    @POST("token")
    suspend fun getToken(
        @Body getTokenBody: GetTokenBody
    ): Response<GetTokenResponse>
}