package com.example.data.remote_storage.models.token

import kotlinx.serialization.Serializable

@Serializable
data class GetTokenResponse(
    val token_type: String,
    val expires_in: String,
    val token: String,
    val refresh_token: String
)