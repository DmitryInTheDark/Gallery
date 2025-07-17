package com.example.data.remote_storage

import com.example.data.remote_storage.models.photo.GetPhotoResponse
import com.example.data.remote_storage.models.register_user.RegisterUserBody
import com.example.data.remote_storage.models.register_user.RegisterUserResponse
import com.example.data.remote_storage.models.token.GetTokenBody
import com.example.data.remote_storage.models.token.GetTokenResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming
import java.io.InputStream

interface ApiInterface {

    @POST("users")
    suspend fun registerUser(
        @Body registerUserBody: RegisterUserBody
    ): Response<RegisterUserResponse>

    @POST("token")
    suspend fun getToken(
        @Body getTokenBody: GetTokenBody
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
    ): Call<ResponseBody>
}