package com.example.data.remote_storage.models.token

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenBody(
    @SerialName("grant_type") val grantType: String,
    @SerialName("refresh_token") val refreshToken: String,
    @SerialName("client_id") val clientID: String,
    @SerialName("client_secret") val clientSecret: String
)
