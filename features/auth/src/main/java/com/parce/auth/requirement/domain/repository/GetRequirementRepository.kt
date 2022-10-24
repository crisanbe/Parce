package com.parce.auth.requirement.domain.repository

import com.parce.auth.requirement.domain.model.RequirementReply
import com.parce.auth.requirement.domain.model.getrequirement.GetRequirement
import com.parce.auth.requirement.domain.model.getrequirement.Pagination
import com.parce.auth.requirement.domain.model.getrequirement.Result
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.io.File

interface GetRequirementRepository {
    fun doGetRequirement(
        token: String,
        current_page: Int,
    ): Flow<Resource<GetRequirement>>

    fun doGetPagination(
        token: String
    ): Flow<Resource<Pagination>>

}