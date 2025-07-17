package com.example.data.remote_storage.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ErrorResponse(
    @SerialName("@context")
    val context: String,
    @SerialName("@type")
    val type: String,
    @SerialName("hydra:title")
    val title: String,
    @SerialName("hydra:description")
    val description: String,
    val violations: List<Violations>?
)

@Serializable
data class Violations(
    val propertyPath: String,
    val message: String,
    val code: String
)