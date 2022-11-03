package com.parce.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class DataResponse(
    val id: Int,
    val description: String,
    val cause_problem: String,
    val efect_problem: String,
    val impact_problem: String,
    val areaintervention: Areaintervention,
    val user: User? = null,
    val relations: Relations? = null,
    val created_at: String? = null
): Serializable