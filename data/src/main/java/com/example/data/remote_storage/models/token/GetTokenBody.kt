package com.example.data.remote_storage.models.token

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTokenBody(
    @SerialName("grant_type") val grantType: String,
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
    @SerialName("client_id") val clientID: String,
    @SerialName("client_secret") val clientSecret: String
)