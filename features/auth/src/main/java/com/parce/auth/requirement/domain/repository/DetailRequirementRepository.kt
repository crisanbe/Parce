package com.parce.auth.requirement.domain.repository

import com.parce.auth.requirement.data.remote.getdetailrequirement.GetDetailResponse
import com.parce.auth.requirement.domain.model.requirement.RequirementReply
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface DetailRequirementRepository {
     fun doDetailRequirement(
         token: String,
         id: Int
    ): Flow<Resource<GetDetailResponse>>
}