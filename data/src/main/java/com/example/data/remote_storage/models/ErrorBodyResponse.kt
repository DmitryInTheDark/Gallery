package com.example.data.remote_storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorBodyResponse(
    @SerialName("@context") val context: String,
    @SerialName("@type") val type: String,
    @SerialName("hydra:title") val title: String,
    @SerialName("hydra:description") val description: String,
)