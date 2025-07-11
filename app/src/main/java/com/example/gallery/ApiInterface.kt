package com.example.gallery

import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiInterface {

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("itemsPerPage") itemsPerPage: Int,
        @Query("order[id]") orderById: String,
        @Query("new") isNew: Boolean,
        @Query("popular") isPopular: Boolean
    ): PhotoListResponse

    @POST("users")
    suspend fun registerUser(
        @Body regUser: RequestUser
    ): RegUser
}

//Прям тут кину этот класс, чтобы далеко не ходить
data class RegUser(
    @SerializedName("@context")
    val contextRef: String,
    @SerializedName("@id")
    val idRef: String,
    @SerializedName("@type")
    val typeRef: String,
    val email: String,
    val userProfilePhoto: String,
    val birthday: String,
    val displayName: String,
    val roles: List<String>,
    val phone: String,
)

//И этот тоже
data class RequestUser(
    @SerializedName("email")
    val email: String,
    @SerializedName("userProfilePhoto")
    val userProfilePhoto: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("plainPassword")
    val plainPassword: String
)

