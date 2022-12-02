package com.gerotac.auth.updateuser.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDto(
    @SerializedName("birthday") val birthday: String? = null,
    @SerializedName("created_at") val created_at: String? = null,
    @SerializedName("document") val document: Int? = null,
    @SerializedName("gener") val gener: String? = null,
    @SerializedName("group_etnic") val group_etnic: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("phone") val phone: Long? = null,
    @SerializedName("presents_disability") val presents_disability: String? = null,
    @SerializedName("academic_program") val academic_program: String? = null,
    @SerializedName("role") val role: String? = null,
    @SerializedName("type_document") val type_document: String? = null,
    @SerializedName("updated_at") val updated_at: String? = null
): Serializable
