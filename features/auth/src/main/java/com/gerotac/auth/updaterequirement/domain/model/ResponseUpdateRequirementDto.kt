package com.gerotac.auth.updaterequirement.domain.model

data class ResponseUpdateRequirementDto(
    val `data`: Data,
    val message: Message,
    val status: Boolean
)