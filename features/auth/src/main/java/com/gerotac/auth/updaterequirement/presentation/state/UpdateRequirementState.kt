package com.gerotac.auth.updaterequirement.presentation.state

import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import com.gerotac.auth.updateuser.data.remote.dto.ReturnUpdateUserDto

data class UpdateRequirementState(
    val isLoading: Boolean = false,
    val updateRequirement: ResponseUpdateRequirementDto? = null,
    val error: String = "",
    val message: String = "",
)
