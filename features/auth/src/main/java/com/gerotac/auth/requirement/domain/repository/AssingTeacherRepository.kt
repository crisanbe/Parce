package com.gerotac.auth.requirement.domain.repository

import com.gerotac.auth.requirement.domain.model.assignrequirement.response.Assign
import com.gerotac.shared.network.Resource

interface AssignTeacherRepository {
    suspend fun doAssignTeacher(
        token: String,
        teacher: List<Int>,
        idRequirement: Int
    ): Resource<Assign>
}