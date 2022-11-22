package com.gerotac.auth.profileUser.domain.model

import java.io.Serializable

data class ResponseProfileUser(
    val status: String,
    val user: List<User>
) : Serializable

data class Teacher(
    val id: Int,
    val user_id: Int
) : Serializable

data class Student(
    val academic_program: AcademicProgram
) : Serializable

data class AcademicProgram(
   val name: String
)

data class User(
    val id: Int,
    val name: String,
    val birthday: String? = null,
    val created_at: String? = null,
    val document: String? = null,
    val email: String,
    val gener: String? = null,
    val group_etnic: String? = null,
    val phone: String? = null,
    val presents_disability: String? = null,
    val academic_program: AcademicProgram,
    val role: String? = null,
    val bussine: Bussine? = null,
    val teacher: Teacher? = null,
    val student: Student? = null,
    val type_document: String? = null,
    val updated_at: String? = null,
    val user_status: String
): Serializable