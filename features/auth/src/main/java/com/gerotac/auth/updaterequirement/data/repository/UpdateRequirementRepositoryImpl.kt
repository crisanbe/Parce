package com.gerotac.auth.updaterequirement.data.repository

import com.gerotac.auth.updaterequirement.data.remote.UpdateRequirementApi
import com.gerotac.auth.updaterequirement.data.remote.dto.request.ResquestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import com.gerotac.auth.updaterequirement.domain.repository.UpdateRequirementRepository
import javax.inject.Inject


class UpdateRequirementRepositoryImpl @Inject constructor(private val api: UpdateRequirementApi) :
    UpdateRequirementRepository {
    override suspend fun doUpdateRequirement(
        token: String,
        id: Int,
        parameterUpdateRequirement: ResquestUpdateRequirement
    ): ResponseUpdateRequirementDto {
        return api.doUpdateRequirement(token = token, id, parameterUpdateRequirement)
    }
}
