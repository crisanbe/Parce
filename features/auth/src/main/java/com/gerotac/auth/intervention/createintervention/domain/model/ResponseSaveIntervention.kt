package com.gerotac.auth.intervention.createintervention.domain.model

data class ResponseSaveInterventionDto(
    val `data`: Data,
    val message: String,
    val status: Boolean
):java.io.Serializable

data class Data(
    val result: List<Result>
):java.io.Serializable

data class Result(
    val created_at: String,
    val description: String,
    val id: Int,
    val relations: List<Any>,
    val requierement: String,
    val status: String,
    val type_intervention: String,
    val user: User
):java.io.Serializable

data class User(
    val id: Int,
    val name: String,
    val role: String
):java.io.Serializable