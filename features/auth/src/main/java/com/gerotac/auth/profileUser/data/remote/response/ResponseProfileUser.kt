package com.gerotac.auth.profileUser.data.remote.response

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.profileUser.domain.model.User
import com.gerotac.auth.profileUser.domain.model.Student
import com.gerotac.auth.profileUser.domain.model.Teacher

data class ResponseProfileUser(
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: List<User>
)

data class Teacher(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val user_id: Int
)

data class Student(
    @SerializedName("academicprogram") val academic_program: AcademicProgram
)

data class AcademicProgram(
    @SerializedName("name") val name: String
)

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("birthday") val birthday: String? = null,
    @SerializedName("created_at") val created_at: String? = null,
    @SerializedName("document") val document: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("gener") val gener: String? = null,
    @SerializedName("group_etnic") val group_etnic: String? = null,
    @SerializedName("phone") val phone: String,
    @SerializedName("presents_disability") val presents_disability: String,
    @SerializedName("academicprogram") val academic_program: AcademicProgram,
    @SerializedName("role") val role: String,
    @SerializedName("bussine") val bussine: Bussine? = null,
    @SerializedName("teacher") val teacher: Teacher? = null,
    @SerializedName("student") val student: Student? = null,
    @SerializedName("type_document") val type_document: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("user_status") val user_status: String
)

fun ResponseProfileUser.toGetProfile(): List<User> {
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
            academic_program = user.academic_program,
            role = user.role,
            student = user.student?.let {
                Student(
                    academic_program = com.gerotac.auth.profileUser.domain.model.AcademicProgram(
                        user.name
                    ),
                )
            },
            teacher = user.teacher?.let {
                Teacher(
                    id = it.id,
                    user_id = user.teacher.user_id
                )
            },
            bussine = user.bussine?.let {
                com.gerotac.auth.profileUser.domain.model.Bussine(
                    activity_economy = it.activity_economy,
                    address = user.bussine.address,
                    departament = user.bussine.departament,
                    id = user.id,
                    municipality = user.bussine.municipality,
                    person_contact = user.bussine.person_contact,
                    type_bussiness = user.bussine.type_bussiness,
                    type_society = user.bussine.type_society,
                    user_id = user.bussine.user_id
                )
            },
            type_document = user.type_document,
            updated_at = user.updated_at,
            user_status = user.user_status,
        )
    }
    return result
}

