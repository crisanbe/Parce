package com.gerotac.auth.requirement.data.remote.requirement

import okhttp3.MultipartBody

data class RequirementRequest(
    val area_intervention: Int,
    val description: String,
    val cause_problem: String,
    val efect_problem: String,
    val impact_problem: String,
    val file: MutableList<MultipartBody.Part> = mutableListOf()
)