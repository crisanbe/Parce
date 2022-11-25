package com.gerotac.auth.profileUser.data.remote.response.respuesta

import com.gerotac.auth.profileUser.domain.model.*
import com.google.gson.annotations.SerializedName

data class ResponseProfileDto(
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: List<User>
)

data class UserDto(
    @SerializedName("birthday") val birthday: String,
    @SerializedName("bussine") val bussine: Bussine? = null,
    @SerializedName("created_at") val created_at: Any? = null,
    @SerializedName("document") val document: String,
    @SerializedName("email") val email: String,
    @SerializedName("gener") val gener: String,
    @SerializedName("group_etnic") val group_etnic: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("presents_disability") val presents_disability: String,
    @SerializedName("role") val role: String,
    @SerializedName("administrador") val administrador: List<Any>? = null,
    @SerializedName("teacher") val teacher: List<Any>? = null,
    @SerializedName("student") val student: Student,
    @SerializedName("type_document") val type_document: String,
    @SerializedName("updated_at") val updated_at: String? = null,
    @SerializedName("user_status") val user_status: String
)

data class BussineDto(
    @SerializedName("activity_economy") val activity_economy: String? = null,
    @SerializedName("address") val address: String,
    @SerializedName("departament") val departament: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("municipality") val municipality: Any? = null,
    @SerializedName("person_contact") val person_contact: String,
    @SerializedName("type_bussiness") val type_bussiness: String,
    @SerializedName("typesociety") val typesociety: Typesociety
)

data class TypesocietyDto(
    @SerializedName("name") val name: String
)

data class StudentDto(
    @SerializedName("academicprogram") val academicprogram: Academicprogram? = null
)

data class AcademicprogramDto(
    @SerializedName("name") val name: String? = null
)

fun ResponseProfileDto.toGetProfile(): List<User> {
    val result = user.mapIndexed { _, user ->
        User(
            id = user.id,
            name = user.name,
            birthday = user.birthday,
            created_at = user.created_at,
            document = user.document,
            email = user.email,
            gener = user.gener,
            group_etnic = user.group_etnic,
            phone = user.phone,
            presents_disability = user.presents_disability,
            type_document = user.type_document,
            updated_at = user.updated_at,
            user_status = user.user_status,
            bussine = Bussine(
                activity_economy = user.bussine?.activity_economy,
                address = user.bussine?.address ?: "",
                departament = user.bussine?.departament ?: 0,
                id = user.bussine?.id ?: 0,
                municipality = user.bussine?.municipality,
                person_contact = user.bussine?.person_contact ?: "",
                type_bussiness = user.bussine?.type_bussiness ?: "",
                typesociety = Typesociety(
                    name = user.bussine?.typesociety?.name ?: ""
                )
            ), role = user.role,
            student = Student(
                academicprogram = Academicprogram(
                    name = user.student?.academicprogram?.name
                )
            ), administrador = user.administrador,
            teacher = user.teacher
        )
    }
    return result
}
