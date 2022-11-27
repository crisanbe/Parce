package com.gerotac.auth.requirement.data.repository

import com.gerotac.auth.requirement.data.remote.api.RequirementApi
import com.gerotac.auth.requirement.data.remote.deleterequirement.ResponseDeleteRequirementDto
import com.gerotac.auth.requirement.domain.repository.DeleteRequirementRepository
import javax.inject.Inject


class DeleteRequirementRepositoryImpl @Inject constructor(private val api: RequirementApi) :
    DeleteRequirementRepository {
    override suspend fun doDeleteRequirement(
        token: String,
        id: Int
    ): ResponseDeleteRequirementDto {
        return api.doDeleteRequirementApi(token = token, id)
    }
}
