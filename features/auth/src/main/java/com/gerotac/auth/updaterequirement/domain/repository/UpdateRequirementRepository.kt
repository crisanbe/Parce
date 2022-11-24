package com.gerotac.auth.updaterequirement.domain.repository

import com.gerotac.auth.updaterequirement.data.remote.dto.request.ResquestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.gerotac.auth.updateuser.data.remote.dto.ReturnUpdateUserDto

interface UpdateRequirementRepository {
    suspend fun doUpdateRequirement(
        token: String,
        id: Int,
        parameterUpdateRequirement: ResquestUpdateRequirement
    ): ResponseUpdateRequirementDto
}