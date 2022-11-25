package com.gerotac.auth.intervention.getintervention.data.remote.getdetailrequirementinterventions

import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.Data

data class GetDetailRequirementListInterventionDto(
    val `data`: Data,
    val message: String,
    val status: Boolean
)