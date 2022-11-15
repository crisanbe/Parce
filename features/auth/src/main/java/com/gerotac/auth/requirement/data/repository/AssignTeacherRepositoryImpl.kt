package com.gerotac.auth.requirement.data.repository

import com.gerotac.auth.requirement.data.remote.api.RequirementApi
import com.gerotac.auth.requirement.data.remote.assignrequirement.response.toGetTeacher
import com.gerotac.auth.requirement.domain.model.assignrequirement.request.AssignRequest
import com.gerotac.auth.requirement.domain.model.assignrequirement.response.Assign
import com.gerotac.auth.requirement.domain.repository.AssignTeacherRepository
import com.gerotac.shared.network.Resource
import javax.inject.Inject

class AssignTeacherRepositoryImpl @Inject constructor(
    private val api: RequirementApi,
) : AssignTeacherRepository {

    override suspend fun doAssignTeacher(
        token: String,
        teacher: List<Int>,
        idRequirement: Int
    ): Resource<Assign> {
        val response = try {
            api.doAssignRequirementApi(
                token = token,
                userTeacher = AssignRequest(
                    userTeacher = teacher,
                    idRequirement = idRequirement
                )
            )
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response.toGetTeacher())
    }
}
