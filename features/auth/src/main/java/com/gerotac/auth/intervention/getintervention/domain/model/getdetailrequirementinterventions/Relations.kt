package com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions

data class Relations(
    val files: List<File>,
    val interventions: List<Intervention>,
    val users: List<UserXX>
)
