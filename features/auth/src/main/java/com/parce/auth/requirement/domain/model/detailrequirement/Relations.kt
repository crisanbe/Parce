package com.parce.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class Relations(
    val files: List<File>,
    val interventions: List<Any>? = emptyList(),
    val users: List<Any>
): Serializable