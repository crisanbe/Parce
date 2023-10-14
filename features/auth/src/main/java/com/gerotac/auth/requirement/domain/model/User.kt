package com.gerotac.auth.requirement.domain.model

import java.io.Serializable

data class UserAnswer(
    val birthday: String,
    val created_at: String,
    val document: String,
    val email: String,
    val gener: String,
    val group_etnic: String,
    val id: Int,
    val name: String,
    val phone: String,
    val presents_disability: String,
    val role: String,
    val type_document: String,
    val updated_at: String,
    val user_status: String
) : Serializable
