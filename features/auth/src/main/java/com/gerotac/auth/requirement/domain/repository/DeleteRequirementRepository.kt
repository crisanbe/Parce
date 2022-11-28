package com.gerotac.auth.requirement.domain.repository

import com.gerotac.auth.requirement.data.remote.deleterequirement.ResponseDeleteRequirementDto

interface DeleteRequirementRepository {
    suspend fun doDeleteRequirement(
        token: String,
        id: Int
    ): ResponseDeleteRequirementDto
}