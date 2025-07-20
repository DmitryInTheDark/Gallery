package com.example.data.remote_storage.models.photo

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSinglePhotoResponse (
    @SerializedName("file") var file: File,
    @SerializedName("user") var user: User,
    @SerializedName("description") var description: String,
    @SerializedName("name") var name: String,
    @SerializedName("new") var new: Boolean,
    @SerializedName("popular") var popular: Boolean,
    @SerializedName("id") var id: Int,
    @SerializedName("dateCreate") var dateCreate: String,
    @SerializedName("dateUpdate") var dateUpdate: String,
)

@Serializable
data class FileSinglePhoto(
    @SerialName("@id") val idRef :String,
    @SerialName("@type") val type: String,
    val path: String,
    val id: Int,
    val dateCreate: String,
    val dateUpdate: String
)

@Serializable
data class User(
    @SerializedName("displayName") var displayName: String,
    @SerializedName("id") var id: Int,
    @SerializedName("dateCreate") var dateCreate: String,
    @SerializedName("dateUpdate") var dateUpdate: String,
)
