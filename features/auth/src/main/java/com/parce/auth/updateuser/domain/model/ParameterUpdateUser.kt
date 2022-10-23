package com.parce.auth.updateuser.domain.model
import java.io.Serializable

data class ParameterUpdateUser(
    val name: String,
    val type_document: String,
    val document: Int,
    val type_bussiness: String,
    val type_society: String,
    val activity_economy: String,
    val phone: Long,
    val person_contact: String,
    val departament: String,
    val municipality: String,
    val address: String,
    val academic_program: String,
    val birthday: String,
    val gener: String,
    val group_etnic: String,
    val presents_disability: String
) : Serializable
