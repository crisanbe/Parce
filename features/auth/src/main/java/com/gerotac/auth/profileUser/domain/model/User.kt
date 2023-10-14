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
    val id: Int,
    val user_id: Int,
    val academic_program: Int
) : Serializable

data class User(
    val id: Int,
    val name: String,
    val birthday: String? = null,
    val created_at: String,
    val document: String? = null,
    val email: String,
    val gener: String? = null,
    val group_etnic: String,
    val phone: String,
    val presents_disability: String,
    val academic_program: Int? = 0,
    val role: String,
    val bussine: Bussine? = null,
    val teacher: Teacher? = null,
    val student: Student? = null,
    val type_document: String,
    val updated_at: String,
    val user_status: String
): Serializable