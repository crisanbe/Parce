package com.gerotac.auth.requirement.data.remote.getdetailrequirement

import java.io.Serializable

data class RelationsDto(
    val files: List<File>,
    val interventions: List<com.gerotac.auth.requirement.domain.model.detailrequirement.Intervention>,
    val users: List<com.gerotac.auth.requirement.domain.model.detailrequirement.User>
)

data class Intervention(
    val created_at: String,
    val description: String,
    val id: Int,
    val relations: List<Any>,
    val requierement: Int,
    val type_intervention: String,
    val user: com.gerotac.auth.requirement.domain.model.detailrequirement.User
) : Serializable
