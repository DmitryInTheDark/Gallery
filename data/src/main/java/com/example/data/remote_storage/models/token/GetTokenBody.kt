package com.example.data.remote_storage.models.token

import kotlinx.serialization.Serializable

@Serializable
data class GetTokenBody(
    val grant_type: String = "password",
    val username: String,
    val password: String,
    val client_id: String = "123",
    val client_secret: String = "123"
)