package com.gerotac.auth.requirement.data.remote.requirement

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.requirement.domain.model.RequirementReply
import com.gerotac.auth.requirement.domain.model.RequirementAnswer
import com.gerotac.auth.requirement.domain.model.UserAnswer
import java.io.Serializable

data class RequirementResponse(
    @SerializedName("message") val message: String,
    @SerializedName("requierement") val requierement: Requirement,
    @SerializedName("status") val status: Boolean
) : Serializable

data class Requirement(
    @SerializedName("area_intervention") val area_intervention: Int,
    @SerializedName("cause_problem") val cause_problem: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("description") val description: String,
    @SerializedName("efect_problem") val efect_problem: String,
    @SerializedName("id") val id: Int,
    @SerializedName("impact_problem") val impact_problem: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("user") val user: User,
    @SerializedName("user_id") val user_id: Int
) : Serializable

data class User(
    @SerializedName("birthday") val birthday: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("document") val document: String,
    @SerializedName("email") val email: String,
    @SerializedName("gener") val gener: String,
    @SerializedName("group_etnic") val group_etnic: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("presents_disability") val presents_disability: String,
    @SerializedName("role") val role: String,
    @SerializedName("type_document") val type_document: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("user_status") val user_status: String
) : Serializable

fun RequirementResponse.toRequirementResponse(): RequirementReply {
    return RequirementReply(
        message = message,
        requierement = RequirementAnswer(
            area_intervention = requierement.area_intervention,
            cause_problem = requierement.cause_problem,
            created_at = requierement.created_at,
            description = requierement.description,
            efect_problem = requierement.efect_problem,
            id = requierement.id,
            impact_problem = requierement.impact_problem,
            updated_at = requierement.updated_at,
            user_id = requierement.user_id,
            user = UserAnswer(
                birthday = requierement.user.birthday,
                created_at = requierement.user.created_at,
                document = requierement.user.document,
                email = requierement.user.email,
                gener = requierement.user.gener,
                group_etnic = requierement.user.group_etnic,
                id = requierement.user.id,
                name = requierement.user.name,
                phone = requierement.user.phone,
                presents_disability = requierement.user.presents_disability,
                role = requierement.user.role,
                type_document = requierement.user.type_document,
                updated_at = requierement.user.updated_at,
                user_status = requierement.user.user_status,
            )
        ),
        status = status
    )
}