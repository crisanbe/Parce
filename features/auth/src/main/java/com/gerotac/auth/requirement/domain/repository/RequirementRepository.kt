package com.gerotac.auth.requirement.domain.repository

import com.gerotac.auth.requirement.domain.model.requirement.RequirementReply
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface RequirementRepository {
    fun doRequirement(
        token: String,
        area_intervention: Int,
        description: RequestBody,
        cause_problem: RequestBody,
        efect_problem: RequestBody,
        impact_problem: RequestBody,
        file: MutableList<MultipartBody.Part> = mutableListOf()
    ): Flow<Resource<RequirementReply>>
}