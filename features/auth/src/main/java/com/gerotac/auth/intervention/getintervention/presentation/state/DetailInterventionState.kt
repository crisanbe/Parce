package com.gerotac.auth.intervention.getintervention.presentation.state

import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.Intervention

data class DetailInterventionState(
    val intervention: List<Intervention> = emptyList(),
    val showPrevious: Boolean = false,
    val showNext: Boolean = false,
    val isLoading: Boolean = false
)
