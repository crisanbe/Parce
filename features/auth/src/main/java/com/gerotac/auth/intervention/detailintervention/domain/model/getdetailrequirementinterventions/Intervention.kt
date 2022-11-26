package com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions

data class Intervention(
    val created_at: String,
    val description: String,
    val id: Int,
    val relations: List<Any>,
    val requierement: Int,
    val type_intervention: String,
    val user: UserXX
)