package com.gerotac.auth.requirement.data.remote.requirementsave

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody

data class RequirementRequest(
    val area_intervention: Int,
    val description: RequestBody,
    val cause_problem: RequestBody,
    val efect_problem: RequestBody,
    val impact_problem: RequestBody,
    val file: MutableList<MultipartBody.Part> = mutableListOf()
)