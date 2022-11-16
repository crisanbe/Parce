package com.gerotac.auth.assignrequirement.data.repository.assignrepository

import com.gerotac.auth.assignrequirement.data.remote.api.AssignRequirementApi
import com.gerotac.auth.requirement.data.remote.api.RequirementApi
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.request.AssignRequest
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.response.AssignDto
import com.gerotac.auth.assignrequirement.domain.repository.AssignTeacherRepository
import javax.inject.Inject

class AssignTeacherRepositoryImpl @Inject constructor(
    private val api: AssignRequirementApi,
) : AssignTeacherRepository {
    override suspend fun doAssignTeacher(
        token: String,
        teacher: AssignRequest
    ): AssignDto {
        return api.doAssignRequirementApi(token,teacher)
    }
}
