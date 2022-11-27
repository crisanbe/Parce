package com.gerotac.auth.requirement.data.repository

import com.gerotac.auth.requirement.data.remote.api.RequirementApi
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import com.gerotac.auth.requirement.domain.repository.RequirementRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RequirementRepositoryImpl @Inject constructor(
    private val api: RequirementApi,
) :
    RequirementRepository {
    override suspend fun doRequirement(
        token: String,
        area_intervention: Int,
        description: RequestBody,
        cause_problem: RequestBody,
        efect_problem: RequestBody,
        impact_problem: RequestBody,
        file: MutableList<MultipartBody.Part>
    ): RequirementResponse {
        return api.doRequirementApi(
            token = token,
            area_intervention = area_intervention,
            description = description,
            cause_problem = cause_problem,
            efect_problem = efect_problem,
            impact_problem = impact_problem,
            document = file
        )
    }
}
