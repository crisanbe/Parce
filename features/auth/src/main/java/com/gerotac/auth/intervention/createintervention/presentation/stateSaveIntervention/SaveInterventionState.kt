package com.gerotac.auth.intervention.createintervention.presentation.stateSaveIntervention

import com.gerotac.auth.intervention.createintervention.data.remote.response.ResponseSaveInterventionDto
import com.gerotac.auth.intervention.createintervention.domain.repository.SaveInterventionRepository
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import com.gerotac.auth.requirement.domain.model.requirement.RequirementReply

data class SaveInterventionState(
    val isLoading: Boolean = false,
    val intervention: ResponseSaveInterventionDto? = null,
    val error: String = "",
    val message: String = ""
)
