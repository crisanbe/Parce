package com.gerotac.auth.approveanddisapprove.presentation.state

import com.gerotac.auth.approveanddisapprove.data.remote.dto.response.ResponseApprove
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import com.gerotac.auth.updateuser.data.remote.dto.ReturnUpdateUserDto

data class ApproveInterventionState(
    val isLoading: Boolean = false,
    val approveIntervention: ResponseApprove? = null,
    val error: String = "",
    val message: String = "",
)
