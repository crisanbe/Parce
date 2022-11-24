package com.gerotac.auth.assignrequirement.data.repository.assignrepository

import com.gerotac.auth.assignrequirement.data.remote.api.AssignRequirementApi
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.request.AssignRequest
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.response.AssignDto
import com.gerotac.auth.assignrequirement.domain.repository.AssignRepository
import javax.inject.Inject

class AssignRepositoryImpl @Inject constructor(
    private val api: AssignRequirementApi,
) : AssignRepository {

    override suspend fun doAssignTeacher(
        token: String,
        teacher: AssignRequest
    ): AssignDto {
        return api.doAssignRequirementTeacherApi(token,teacher)
    }

    override suspend fun doAssignStudent(
        token: String,
        teacher: AssignRequest
    ): AssignDto {
        return api.doAssignRequirementTeacherApi(token,teacher)
    }
}
