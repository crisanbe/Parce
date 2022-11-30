package com.gerotac.auth.profileUser.domain.model

data class User(
    val birthday: String? = null,
    val bussine: Bussine? = null,
    val created_at: Any? = null,
    val document: String? = null,
    val email: String,
    val gener: String? = null,
    val group_etnic: String? = null,
    val id: Int,
    val name: String,
    val phone: String,
    val presents_disability: String? = null,
    val role: String,
    val administrador: List<Any>? = null,
    val teacher: List<Any>? = null,
    val student: Student? = null,
    val type_document: String? = null,
    val updated_at: String? = null,
    val user_status: String
)

data class Bussine(
    val activity_economy: String? = null,
    val address: String,
    val departament: Int,
    val id: Int,
    val municipality: Any? = null,
    val person_contact: String,
    val type_bussiness: String,
    val typesociety: Typesociety
)

data class Typesociety(
    val name: String
)

data class Student(
    val academicprogram: Academicprogram? = null
)

data class Academicprogram(
    val name: String? = null
)