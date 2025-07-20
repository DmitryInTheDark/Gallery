package com.example.data.remote_storage.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserBody(
    val email: String,
    val oldPassword: String,
    val birthday: String,
    val displayName: String,
    val phone: String,
    val plainPassword: String
)
