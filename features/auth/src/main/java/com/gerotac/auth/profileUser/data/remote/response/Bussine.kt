package com.gerotac.auth.profileUser.data.remote.response

data class Bussine(
    val activity_economy: String,
    val address: String,
    val departament: Int,
    val id: Int,
    val municipality: Int,
    val person_contact: String,
    val type_bussiness: String,
    val type_society: TypeSociety,
    val user_id: Int
): java.io.Serializable

data class TypeSociety(
    val name: String,
): java.io.Serializable