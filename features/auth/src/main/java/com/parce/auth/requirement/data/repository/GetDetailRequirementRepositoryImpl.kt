package com.parce.auth.requirement.data.repository

import com.google.gson.Gson
import com.parce.auth.requirement.data.remote.api.RequirementApi
import com.parce.auth.requirement.data.remote.getdetailrequirement.toGetDetail
import com.parce.auth.requirement.data.remote.getdetailrequirement.toGetDetailRequirement
import com.parce.auth.requirement.domain.model.detailrequirement.Data
import com.parce.auth.requirement.domain.model.detailrequirement.DetailResponse
import com.parce.auth.requirement.domain.repository.DetailRequirementRepository
import com.parce.auth.requirement.presentation.state.DetailRequirementState
import com.parce.auth.requirement.presentation.state.RequirementState
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailRequirementRepositoryImpl @Inject constructor(
    private val api: RequirementApi,
) : DetailRequirementRepository {

    override suspend fun doDetailRequirement(
        token: String,
        id: Int
    ): Resource<Data> {
        return try {
            val response = api.doGetDetailRequirementApi(token = token, id = id).toGetDetail()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("An unknown error occurred")
        }
    }
}
