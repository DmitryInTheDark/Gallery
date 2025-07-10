package com.example.gallery

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiInterface {


    @GET("users")
    fun getUserList(
        @Query("page") page: Int,
        @Query("itemsPerPage") itemsPerPage: Int
    ): Call<User>

    @POST("token")
    fun login(
        @Query("grant_type") grant_type: String,
        @Query("username") username: String?,
        @Query("password") password: String?,
        @Query("client_id") client_id: String?,
        @Query("client_secret") client_secret: String?,
    ): Call<User>
}