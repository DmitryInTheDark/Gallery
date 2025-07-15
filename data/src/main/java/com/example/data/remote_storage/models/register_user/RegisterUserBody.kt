package com.example.data.remote_storage.models.register_user

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserBody(
    val email: String,
    val birthday: String,
    val displayName: String,
    val phone: String,
    val plainPassword: String
)