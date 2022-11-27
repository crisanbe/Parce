package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.data.remote.deleterequirement.ResponseDeleteRequirementDto
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import com.gerotac.auth.updateuser.data.remote.dto.ReturnUpdateUserDto

data class DeleteRequirementState(
    val isLoading: Boolean = false,
    val deleteRequirement: ResponseDeleteRequirementDto? = null,
    val error: String = "",
    val message: String = "",
)
