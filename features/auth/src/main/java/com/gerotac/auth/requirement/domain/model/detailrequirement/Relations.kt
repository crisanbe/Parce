package com.gerotac.auth.requirement.domain.model.detailrequirement

data class Relations(
    val files: List<File>,
    val interventions: List<Any>? = emptyList(),
    val users: List<User>? = emptyList()
)