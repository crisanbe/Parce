package com.gerotac.auth.requirement.domain.model.detailrequirement

data class DataResponse(
    val areaintervention: Areaintervention,
    val cause_problem: String,
    val created_at: String,
    val description: String,
    val efect_problem: String,
    val id: Int? = 0,
    val impact_problem: String,
    val relations: Relations,
    val user: User
)
