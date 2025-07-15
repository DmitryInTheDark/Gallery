package com.example.data.remote_storage.models.register_user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserResponse(
    @SerialName("@context")
    val context: String,
    @SerialName("@id")
    val id :String,
    @SerialName("@type")
    val type: String,
    val email: String,
    val birthday: String,
    val displayName: String,
    val roles: List<String>,
    val phone: String
)