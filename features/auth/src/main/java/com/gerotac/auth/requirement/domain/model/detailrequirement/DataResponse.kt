package com.gerotac.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class DataResponse(
    val id: Int,
    val description: String? = null,
    val cause_problem: String? = null,
    val efect_problem: String? = null,
    val impact_problem: String? = null,
    val areaintervention: Areaintervention,
    val user: User? = null,
    val relations: Relations? = null,
    val created_at: String? = null
): Serializable