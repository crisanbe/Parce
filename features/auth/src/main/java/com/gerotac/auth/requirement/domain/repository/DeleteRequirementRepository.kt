package com.gerotac.auth.requirement.domain.repository

import com.gerotac.auth.requirement.data.remote.deleterequirement.ResponseDeleteRequirementDto
import com.gerotac.auth.updaterequirement.data.remote.dto.request.ResquestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto

interface DeleteRequirementRepository {
    suspend fun doDeleteRequirement(
        token: String,
        id: Int
    ): ResponseDeleteRequirementDto
}