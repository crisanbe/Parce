package com.gerotac.auth.updaterequirement.data.remote.dto.response

data class Result(
    val areaintervention: Areaintervention,
    val cause_problem: String,
    val created_at: String,
    val description: String,
    val efect_problem: String,
    val id: Int,
    val impact_problem: String,
    val relations: List<Any>,
    val user: User
)