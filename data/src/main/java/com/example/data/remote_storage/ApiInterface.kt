package com.example.data.remote_storage

import com.example.data.remote_storage.models.photo.GetPhotoResponse
import com.example.data.remote_storage.models.photo.GetSinglePhotoResponse
import com.example.data.remote_storage.models.register_user.RegisterUserBody
import com.example.data.remote_storage.models.register_user.RegisterUserResponse
import com.example.data.remote_storage.models.token.GetTokenBody
import com.example.data.remote_storage.models.token.GetTokenResponse
import com.example.data.remote_storage.models.token.RefreshTokenBody
import com.example.data.remote_storage.models.user.CurrentUserResponse
import com.example.data.remote_storage.models.user.UpdateUserBody
import com.example.data.remote_storage.models.user.UpdateUserResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming

interface ApiInterface {

    @POST("users")
    suspend fun registerUser(
        @Body registerUserBody: RegisterUserBody
    ): Response<RegisterUserResponse>

    @POST("token")
    suspend fun getToken(
        @Body getTokenBody: GetTokenBody
    ): Response<GetTokenResponse>

    @POST("token")
    suspend fun refreshToken(
        @Body getTokenBody: RefreshTokenBody
    ): Response<GetTokenResponse>

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int = 1,
        @Query("new") isNew: Boolean,
        @Query("popular") isPopular: Boolean
    ): Response<GetPhotoResponse>

    @Streaming
    @GET("get_file/{path}")
    suspend fun getFile(
        @Path("path") path: String
    ): Response<ResponseBody>

    @GET("photos/{id}")
    suspend fun getPhotoByID(
        @Path("id") id: String
    ): Response<GetSinglePhotoResponse>

    @GET("current")
    suspend fun getCurrentUser(): Response<CurrentUserResponse>

    @PATCH("users/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body updateUserBody: UpdateUserBody
    ): Response<UpdateUserResponse>

    @PATCH("photos/{id}")
    suspend fun updatePhoto(
        @Path("id") id: String,
        @Body updateUserBody: UpdateUserBody
    ): Response<UpdateUserResponse>

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Path("id") id: String
    ): Response<String>
}