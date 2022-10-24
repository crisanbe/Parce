package com.parce.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class User(
    val name: String,
    val role: String
): Serializable