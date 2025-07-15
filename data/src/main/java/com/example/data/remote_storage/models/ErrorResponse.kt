package com.example.data.remote_storage.models

import kotlinx.serialization.SerialName

data class ErrorResponse(
    @SerialName("@context")
    val context: String,
    @SerialName("@type")
    val type: String,
    @SerialName("hydra:title")
    val title: String,
    @SerialName("sydra:description")
    val description: String
)