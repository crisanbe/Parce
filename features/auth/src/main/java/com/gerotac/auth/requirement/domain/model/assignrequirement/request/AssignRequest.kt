package com.gerotac.auth.requirement.domain.model.assignrequirement.request

data class AssignRequest(
    val userTeacher: List<Int>,
    val idRequirement: Int
)
