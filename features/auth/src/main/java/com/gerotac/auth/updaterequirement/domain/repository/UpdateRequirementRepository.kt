package com.gerotac.auth.updaterequirement.domain.repository

import com.gerotac.auth.updaterequirement.data.remote.dto.request.RequestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto

interface UpdateRequirementRepository {
    suspend fun doUpdateRequirement(
        token: String,
        id: Int,
        parameterUpdateRequirement: RequestUpdateRequirement
    ): ResponseUpdateRequirementDto
}