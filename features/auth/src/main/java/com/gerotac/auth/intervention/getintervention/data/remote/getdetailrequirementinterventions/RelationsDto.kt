package com.gerotac.auth.intervention.getintervention.data.remote.getdetailrequirementinterventions

import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.File
import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.Intervention
import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.UserXX

data class RelationsDto(
    val files: List<File>,
    val interventions: List<Intervention>? = emptyList(),
    val users: List<UserXX>
)

fun RelationsDto.toGetDetailRequirementListIntervention(): List<Intervention>? {
    val resultIntervention = interventions?.mapIndexed { _, result ->
        Intervention(
            created_at = result.created_at,
            description = result.description,
            id = result.id,
            relations = result.relations,
            requierement = result.requierement,
            type_intervention = result.type_intervention,
            user = result.user
        )
    }
    return resultIntervention
}