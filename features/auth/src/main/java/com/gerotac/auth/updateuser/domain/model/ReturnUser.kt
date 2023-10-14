package com.gerotac.auth.updateuser.domain.model

import java.io.Serializable

data class ReturnUser(
    val birthday: String,
    val created_at: String,
    val document: Int,
    val gener: String,
    val group_etnic: String,
    val name: String,
    val phone: Long,
    val presents_disability: String,
    val academic_program: String,
    val role: String,
    val type_document: String,
    val updated_at: String
) : Serializable
