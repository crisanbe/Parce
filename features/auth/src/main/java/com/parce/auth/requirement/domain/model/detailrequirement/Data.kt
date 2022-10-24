package com.parce.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class Data(
    val areaintervention: Areaintervention,
    val cause_problem: String,
    val created_at: String,
    val description: String,
    val efect_problem: String,
    val id: Int,
    val impact_problem: String,
    val relations: Relations,
    val user: User
): Serializable