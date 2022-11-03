package com.parce.auth.requirement.data.repository

import com.parce.auth.requirement.data.remote.api.RequirementApi
import com.parce.auth.requirement.data.remote.getdetailrequirement.toGetDetail
import com.parce.auth.requirement.domain.model.detailrequirement.DataResponse
import com.parce.auth.requirement.domain.model.detailrequirement.FileResponse
import com.parce.auth.requirement.domain.repository.DetailRequirementRepository
import com.parce.shared.network.Resource
import javax.inject.Inject

class GetDetailRequirementRepositoryImpl @Inject constructor(
    private val api: RequirementApi,
) : DetailRequirementRepository {

    override suspend fun doDetailRequirement(
        token: String,
        id: Int
    ): Resource<DataResponse> {
        val response = try {
            api.doGetDetailRequirementApi(token = token, id = id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response.data.toGetDetail())
    }
}
