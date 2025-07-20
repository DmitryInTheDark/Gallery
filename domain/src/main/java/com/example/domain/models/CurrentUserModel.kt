package com.example.domain.models

data class CurrentUserModel(
    val email: String,
    val birthday: String,
    val displayName: String,
    val phone: String,
    val id: Int,
    val dateCreate: String
)

