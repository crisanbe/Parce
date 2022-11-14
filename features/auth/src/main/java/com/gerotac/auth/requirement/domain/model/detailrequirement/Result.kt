package com.gerotac.auth.requirement.domain.model.detailrequirement

import com.gerotac.auth.requirement.data.remote.getdetailrequirement.Areaintervention
import com.gerotac.auth.requirement.data.remote.getdetailrequirement.User

data class Result(
    val areaintervention: Areaintervention? = null,
    val cause_problem: String? = null,
    val created_at: String? = null,
    val description: String? = null,
    val efect_problem: String? = null,
    val id: Int? = null,
    val impact_problem: String? = null,
    val relations: Relations? = null,
    val user: User? = null
)