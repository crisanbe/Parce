package com.gerotac.auth.intervention.createintervention.data.repository

import com.gerotac.auth.intervention.createintervention.data.remote.api.SaveInterventionApi
import com.gerotac.auth.intervention.createintervention.data.remote.response.ResponseSaveInterventionDto
import com.gerotac.auth.intervention.createintervention.domain.repository.SaveInterventionRepository
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class SaveInterventionRepositoryImpl @Inject constructor(
    private val api: SaveInterventionApi,
) :
    SaveInterventionRepository {
    override suspend fun doSaveIntervention(
        token: String,
        type_intervention: RequestBody,
        description: RequestBody,
        requierement_id: Int,
        file: MutableList<MultipartBody.Part>
    ): ResponseSaveInterventionDto {
        return api.doSaveInterventionApi(
            token = token,
            type_intervention = type_intervention,
            description = description,
            requierement_id = requierement_id,
            document = file
        )
    }
}
