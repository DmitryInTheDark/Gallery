package com.example.data.remote_storage.models.photo

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPhotoResponse(
    @SerialName("@context") val dogContext: String,
    @SerialName("@id") val dogId: String,
    @SerialName("@type") val dogType: String,
    @SerialName("hydra:totalItems") val totalItems: Int,
    @SerialName("hydra:member") val member: List<Member>,
    @SerialName("hydra:view") val view: View,
    @SerialName("hydra:search") val search: Search
)

@Serializable
data class Member(
    @SerialName("@id") val dogId: String,
    @SerialName("@type") val dogType: String,
    val file: File,
    val new: Boolean,
    val popular: Boolean,
    val id: Int
)

@Serializable
data class File(
    @SerialName("@id") val dogId: String,
    @SerialName("@type") val dogType: String,
    val path: String,
    val id: Int
)

@Serializable
data class View(
    @SerialName("@id") val dogId: String,
    @SerialName("@type") val dogType: String
)

@Serializable
data class Search(
    @SerialName("@type") val dogType: String,
    @SerialName("hydra:template")val template: String,
    @SerialName("hydra:variableRepresentation") val variableRepresentation: String,
    @SerialName("hydra:mapping") val mapping: List<Mapping>
)

@Serializable
data class Mapping(
    @SerialName("@type") val dogType: String,
    val variable: String,
    val property: String,
    val required: Boolean
)