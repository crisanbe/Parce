package com.gerotac.auth.intervention.getintervention.data.remote.getdetailrequirementinterventions

import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.Areaintervention
import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.Relations
import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.UserXX

data class Data(
    val areaintervention: Areaintervention,
    val cause_problem: String,
    val created_at: String,
    val description: String,
    val efect_problem: String,
    val id: Int,
    val impact_problem: String,
    val relations: Relations,
    val user: UserXX
)
