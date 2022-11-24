package com.gerotac.auth.updaterequirement.data.repository

import com.gerotac.auth.updaterequirement.data.remote.UpdateRequirementApi
import com.gerotac.auth.updaterequirement.data.remote.dto.request.ResquestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import com.gerotac.auth.updaterequirement.domain.repository.UpdateRequirementRepository
import com.gerotac.auth.updateuser.data.remote.UpdateUserApi
import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.gerotac.auth.updateuser.data.remote.dto.ReturnUpdateUserDto
import com.gerotac.auth.updateuser.domain.repository.UpdateUserRepository
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
