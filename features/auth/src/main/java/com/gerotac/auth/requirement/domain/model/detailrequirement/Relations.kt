package com.gerotac.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class Relations(
    val files: List<File>,
    val interventions: List<Intervention>? = null,
    val users: List<User>? = null
)

data class Intervention(
    val created_at: String,
    val description: String,
    val id: Int,
    val relations: List<Any>,
    val requierement: Int,
    val status: String? = null,
    val type_intervention: String,
    val user: User
) : Serializable