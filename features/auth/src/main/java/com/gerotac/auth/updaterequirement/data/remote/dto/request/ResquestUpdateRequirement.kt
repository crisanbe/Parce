package com.gerotac.auth.updaterequirement.data.remote.dto.request

data class ResquestUpdateRequirement(
    val area_intervention: Int,
    val cause_problem: String,
    val description: String,
    val efect_problem: String,
    val impact_problem: String
)