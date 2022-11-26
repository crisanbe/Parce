package com.gerotac.auth.intervention.getintervention.data.remote.getdetailrequirementinterventions

import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.UserXX

data class InterventionDto(
    val created_at: String,
    val description: String,
    val id: Int,
    val relations: List<Any>,
    val requierement: Int,
    val type_intervention: String,
    val user: UserXX
)