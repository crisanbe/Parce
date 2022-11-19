package com.gerotac.auth.assignrequirement.data.remote.api

import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.request.AssignRequest
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.response.AssignDto
import retrofit2.http.*

interface AssignRequirementApi {
    @POST("requierements-assign")
    suspend fun doAssignRequirementApi(
        @Header("Authorization") token: String,
        @Body userTeacher: AssignRequest,
    ): AssignDto

    @POST("requierements-assign-student")
    suspend fun doAssignRequirementStudentApi(
        @Header("Authorization") token: String,
        @Body userStudent: AssignRequest,
    ): AssignDto
}