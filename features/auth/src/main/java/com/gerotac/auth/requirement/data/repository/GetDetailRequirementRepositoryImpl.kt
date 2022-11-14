package com.gerotac.auth.requirement.data.repository

import com.gerotac.auth.requirement.data.remote.api.RequirementApi
import com.gerotac.auth.requirement.data.remote.getdetailrequirement.toGetDetail
import com.gerotac.auth.requirement.domain.model.detailrequirement.Data
import com.gerotac.auth.requirement.domain.repository.DetailRequirementRepository
import com.gerotac.shared.network.Resource
import javax.inject.Inject

class GetDetailRequirementRepositoryImpl @Inject constructor(
    private val api: RequirementApi,
) : DetailRequirementRepository {

    override suspend fun doDetailRequirement(
        token: String,
        id: Int
    ): Resource<Data> {
        val response = try {
            api.doGetDetailRequirementApi(token = token, id = id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response.toGetDetail())
    }
}
