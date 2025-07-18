package com.example.data.remote_storage.models.token

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTokenResponse(
    @SerialName("token_type") val tokenType: String,
    @SerialName("expires_in") val expiresIn: Int,
    @SerialName("access_token") val token: String,
    @SerialName("refresh_token") val refreshToken: String
)