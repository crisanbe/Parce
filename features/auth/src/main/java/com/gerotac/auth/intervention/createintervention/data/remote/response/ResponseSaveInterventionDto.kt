package com.gerotac.auth.intervention.createintervention.data.remote.response

data class ResponseSaveInterventionDto(
    val `data`: DataDto,
    val message: String,
    val status: Boolean
):java.io.Serializable

data class DataDto(
    val result: List<ResultDto>
):java.io.Serializable

data class ResultDto(
    val created_at: String,
    val description: String,
    val id: Int,
    val relations: List<Any>,
    val requierement: String,
    val status: String,
    val type_intervention: String,
    val user: UserDto
):java.io.Serializable

data class UserDto(
    val id: Int,
    val name: String,
    val role: String
):java.io.Serializable