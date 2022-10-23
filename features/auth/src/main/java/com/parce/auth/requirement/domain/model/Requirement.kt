package com.parce.auth.requirement.domain.model

import java.io.Serializable

data class RequirementAnswer(
    val area_intervention: Int,
    val cause_problem: String,
    val created_at: String,
    val description: String,
    val efect_problem: String,
    val id: Int,
    val impact_problem: String,
    val updated_at: String,
    val user: UserAnswer,
    val user_id: Int
): Serializable
