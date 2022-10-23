package com.parce.auth.requirement.domain.repository

import com.parce.auth.requirement.domain.model.RequirementReply
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.io.File

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