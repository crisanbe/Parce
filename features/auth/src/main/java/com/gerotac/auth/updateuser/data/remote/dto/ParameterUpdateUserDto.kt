package com.gerotac.auth.updateuser.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ParameterUpdateUserRequest(
    @SerializedName("name") val name: String? = null,
    @SerializedName("type_document") val type_document: String?,
    @SerializedName("document") val document: String? = null,
    @SerializedName("type_bussiness") val type_bussiness: String? = null,
    @SerializedName("type_society") val type_society: Int? = 0,
    @SerializedName("activity_economy") val activity_economy: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("person_contact") val person_contact: String? = null,
    @SerializedName("departament") val departament: Int? = null,
    @SerializedName("municipality") val municipality: Int? = null,
    @SerializedName("address") val address: String? = null,
    @SerializedName("academic_program") val academic_program: Int? = 0,
    @SerializedName("birthday") val birthday: String? = null,
    @SerializedName("gener") val gener: String? = null,
    @SerializedName("group_etnic") val group_etnic: String? = null,
    @SerializedName("rol") val rol: String? = null,
    @SerializedName("presents_disability") val presents_disability: String? = null
) : Serializable