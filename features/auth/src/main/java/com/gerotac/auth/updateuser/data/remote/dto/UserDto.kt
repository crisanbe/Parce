package com.gerotac.auth.updateuser.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDto(
    @SerializedName("birthday") val birthday: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("document") val document: Int,
    @SerializedName("gener") val gener: String,
    @SerializedName("group_etnic") val group_etnic: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: Long,
    @SerializedName("presents_disability") val presents_disability: String,
    @SerializedName("academic_program") val academic_program: String,
    @SerializedName("role") val role: String,
    @SerializedName("type_document") val type_document: String,
    @SerializedName("updated_at") val updated_at: String
): Serializable
