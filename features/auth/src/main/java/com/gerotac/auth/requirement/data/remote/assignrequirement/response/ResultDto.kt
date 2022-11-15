package com.gerotac.auth.requirement.data.remote.assignrequirement.response

import com.gerotac.auth.requirement.domain.model.assignrequirement.response.Areaintervention
import com.gerotac.auth.requirement.domain.model.assignrequirement.response.User

data class ResultDto(
    val areaintervention: Areaintervention,
    val cause_problem: String,
    val created_at: String,
    val description: String,
    val efect_problem: String,
    val id: Int,
    val impact_problem: String,
    val relations: List<Any>? = null,
    val user: User? = null
)