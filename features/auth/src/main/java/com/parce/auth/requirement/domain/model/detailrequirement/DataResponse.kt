package com.parce.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class DataResponse(
    val areaintervention: Areaintervention? = null,
    val cause_problem: String? = null,
    val created_at: String? = null,
    val description: String? = null,
    val efect_problem: String? = null,
    val id: Int? = null,
    val impact_problem: String? = null,
    val relations: Relations? = null,
    val user: User? = null
): Serializable