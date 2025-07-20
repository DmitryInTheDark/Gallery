package com.example.domain.models

data class UpdateUserModel(
    val email: String,
    val oldPassword: String,
    val userProfilePhoto: String,
    val birthday: String,
    val displayName: String,
    val phone: String,
    val newPassword: String
)
