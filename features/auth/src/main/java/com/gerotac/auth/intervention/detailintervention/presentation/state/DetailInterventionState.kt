package com.gerotac.auth.intervention.detailintervention.presentation.state

import com.gerotac.auth.intervention.detailintervention.domain.model.DetailResponseIntervention
import com.gerotac.auth.intervention.detailintervention.domain.model.File

data class DetailInterventionState(
    val intervention: DetailResponseIntervention? = null,
    val fileIntervention: List<File> = emptyList(),
    val isLoading: Boolean = false
)
