package com.example.domain.models

data class RegisterUserModel(
    val userName: String,
    val birthday: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)