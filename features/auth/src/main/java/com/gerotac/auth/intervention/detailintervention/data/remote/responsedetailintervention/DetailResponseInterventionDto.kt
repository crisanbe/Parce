package com.gerotac.auth.intervention.detailintervention.data.remote.responsedetailintervention

import com.gerotac.auth.intervention.detailintervention.domain.model.*
import com.google.gson.annotations.SerializedName

data class DetailResponseInterventionDto(
    @SerializedName("data") val data: Data,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Boolean
)

data class DataDto(
    @SerializedName("created_at") val created_at: String,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("relations") val relations: Relations,
    @SerializedName("requierement") val requierement: Int,
    @SerializedName("type_intervention") val type_intervention: String,
    @SerializedName("user") val user: User
)

data class RelationsDto(
    @SerializedName("files") val files: List<File>
)

data class FileDto(
    @SerializedName("created_at") val created_at: String,
    @SerializedName("filename") val filename: String,
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String
)

data class UserDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String
)

fun DetailResponseInterventionDto.toGetDetailIntervention(): DetailResponseIntervention {
    return DetailResponseIntervention(
        data = Data(
            created_at = data.created_at,
            description = data.description,
            id = data.id,
            relations = Relations(
                files = data.relations.files
            ),
            user = data.user,
            requierement = data.requierement,
            type_intervention = data.type_intervention
        ),
        message = message,
        status = status
    )
}