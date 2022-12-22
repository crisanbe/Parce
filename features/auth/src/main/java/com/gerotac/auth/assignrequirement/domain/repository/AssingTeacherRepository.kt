package com.gerotac.auth.assignrequirement.domain.repository

import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.request.AssignRequest
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.response.AssignDto

interface AssignTeacherRepository {
    suspend fun doAssignTeacher(
        token: String,
        teacher: AssignRequest
    ): AssignDto
}