package com.gerotac.auth.approveanddisapprove.presentation.state

import com.gerotac.auth.approveanddisapprove.data.remote.dto.response.ResponseApprove

data class ApproveAndDisapproveInterventionState(
    val isLoading: Boolean = false,
    val approveIntervention: ResponseApprove? = null,
    val error: String = "",
    val message: String = "",
)
