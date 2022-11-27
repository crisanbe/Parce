package com.gerotac.auth.intervention.createintervention.domain.repository

import com.gerotac.auth.intervention.createintervention.data.remote.response.ResponseSaveInterventionDto
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import com.gerotac.auth.requirement.domain.model.requirement.RequirementReply
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface SaveInterventionRepository {
   suspend fun doSaveIntervention(
        token: String,
        type_intervention: RequestBody,
        description: RequestBody,
        requierement_id: Int,
        file: MutableList<MultipartBody.Part> = mutableListOf()
    ): ResponseSaveInterventionDto
}