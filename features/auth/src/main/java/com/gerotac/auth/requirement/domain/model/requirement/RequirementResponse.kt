package com.gerotac.auth.requirement.domain.model.requirement

import java.io.Serializable

data class RequirementReply(
    val message: String,
    val requierement: RequirementAnswer,
    val status: Boolean
): Serializable