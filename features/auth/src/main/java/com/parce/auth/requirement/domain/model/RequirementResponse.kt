package com.parce.auth.requirement.domain.model

import java.io.Serializable

data class RequirementReply(
    val message: String,
    val requierement: RequirementAnswer,
    val status: Boolean
): Serializable