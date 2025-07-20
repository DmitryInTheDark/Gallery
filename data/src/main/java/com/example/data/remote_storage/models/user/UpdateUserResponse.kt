package com.example.data.remote_storage.models.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserResponse(
    @SerialName("@context") val context: String,
    @SerialName("@id") val idRef: String,
    @SerialName("@type") val type: String,
    val email: String,
    val birthday: String,
    val displayName: String,
    val roles: List<String>,
    val phone: String,
)

