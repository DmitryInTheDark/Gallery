package com.example.gallery.fragments.GalleryFragments.recycler_adapters

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Photo(
    @param:Json(name = "hydra:member") val hydraMember: HydraMember,
    @param:Json(name = "hydra:totalItems") val hydraTotalItems: Int,
    @param:Json(name = "hydra:view") val hydraView: HydraView,
    @param:Json(name = "hydra:search") val hydraSearch: HydraSearch
)

data class HydraMapping(
    @param:Json(name = "@type") val type: String,
    val variable: String,
    val property: String,
    val required: Boolean
)

data class HydraSearch(
    @param:Json(name = "@type") val type: String,
    @param:Json(name = "hydra:template") val hydraTemplate: String,
    @param:Json(name = "hydra:variableRepresentation") val hydraVariableRepresentation: String,
    @param:Json(name = "hydra:mapping") val hydraMapping: HydraMapping
)
data class HydraView(
    @param:Json(name = "@id") val idRef: String,
    val type: String,
    val hydraFirst: String,
    val hydraPrevious: String,
    val hydraNext: String
)


data class HydraMember(
    @param:Json(name = "@context") val context: String,
    @param:Json(name = "@id") val idRef: String,
    @param:Json(name = "@type") val type: String,
    val user: User,
    val description: String,
    val name: String,
    val new: Boolean,
    val popular: Boolean,
    val id: Int,
    val dateCreate: String,
    val dateUpdate: String,
    val deleted: String
)



@JsonClass(generateAdapter = true)
data class File(
    @param:Json(name = "@context") val context: String,
    @param:Json(name = "id") val idRef: String,
    @param:Json(name = "@type") val type: String,
    val originalName: String,
    val path: String,
    val fullPath: String,
    val mimeType: String,
    val id: Int,
    val dateCreate: String,
    val dateUpdate: String,
    val deleted: String
)

data class User(
    @param:Json(name = "@context") val context: String,
    @param:Json(name = "@id") val idRef: String,
    @param:Json(name = "@type") val type: String,
    val displayName: String,
    val id: Int,
    val dateCreate: String,
    val dateUpdate: String,
    val deleted: String
)