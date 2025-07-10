package com.example.gallery

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("hydra:member")
    val memberList: List<Member>,
    @SerializedName("hydra:totalItems")
    val totalItems: Int,
    @SerializedName("hydra:view")
    val view: UserView,
    @SerializedName("hydra:search")
    val search: UserSearch
)
data class Member(
    @SerializedName("@id")
    val dogId: String,
    @SerializedName("@type")
    val dogType: String,
    val email: String,
    val userProfilePhoto: String,
    val birthday: String,
    val displayName: String,
    val roles: List<String>,
    val phone:String
)
data class UserView(
    @SerializedName("@id")
    val dogId: String,
    val type: String,
    @SerializedName("hydra:first")
    val first: String,
    @SerializedName("hydra:last")
    val last: String,
    @SerializedName("hydra:previous")
    val previous: String,
    @SerializedName("hydra:next")
    val next: String
)
data class UserSearch(
    @SerializedName("@type")
    val dogType: String,
    @SerializedName("hydra:template")
    val template: String,
    @SerializedName("hydra:variableRepresentation")
    val variableRepresentation: String,
    @SerializedName("hydra:mapping")
    val mapping: List<Mapping>,
)

data class Mapping(
    @SerializedName("@type")
    val type: String,
    val variable: String,
    val property: String,
    val required: Boolean
)
