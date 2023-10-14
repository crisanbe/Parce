package com.gerotac.auth.requirement.domain.repository

import com.gerotac.auth.requirement.domain.model.RequirementReply
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface RequirementRepository {
     fun doRequirement(
         token: String,
         area_intervention: Int,
         description: String,
         cause_problem: String,
         efect_problem: String,
         impact_problem: String,
         file: MutableList<MultipartBody.Part> = mutableListOf()
    ): Flow<Resource<RequirementReply>>
}