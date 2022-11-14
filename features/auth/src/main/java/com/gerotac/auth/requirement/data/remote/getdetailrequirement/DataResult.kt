package com.gerotac.auth.requirement.data.remote.getdetailrequirement

data class DataResult(
    val areaintervention: Areaintervention,
    val cause_problem: String,
    val created_at: String,
    val description: String,
    val efect_problem: String,
    val id: Int,
    val impact_problem: String,
    val relations: Relations,
    val user: User
)
