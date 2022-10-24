package com.parce.auth.requirement.domain.model.requirement

import com.parce.auth.requirement.domain.model.requirement.RequirementAnswer
import java.io.Serializable

data class RequirementReply(
    val message: String,
    val requierement: RequirementAnswer,
    val status: Boolean
): Serializable