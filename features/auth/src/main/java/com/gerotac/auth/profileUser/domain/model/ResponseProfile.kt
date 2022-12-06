package com.gerotac.auth.profileUser.domain.model

data class User(
    val birthday: String? = null,
    val bussine: Bussine? = null,
    val created_at: Any? = null,
    val document: String? = null,
    val email: String? = null,
    val gener: String? = null,
    val group_etnic: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val phone: String? = null,
    val presents_disability: String? = null,
    val role: String? = null,
    val administrador: List<Any>? = null,
    val teacher: List<Any>? = null,
    val student: Student? = null,
    val type_document: String? = null,
    val updated_at: String? = null,
    val user_status: String? = null
)

data class Bussine(
    val activity_economy: String? = null,
    val address: String? = null,
    val departament: Int? = null,
    val id: Int? = null,
    val municipality: Int? = null,
    val person_contact: String? = null,
    val type_bussiness: String? = null,
    val typesociety: Typesociety? = null
)

data class Typesociety(
    val name: String? = null
)

data class Student(
    val academicprogram: Academicprogram? = null
)

data class Academicprogram(
    val name: String? = null
)