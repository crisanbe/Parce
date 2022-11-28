package com.gerotac.auth.approveanddisapprove.domain.repository

import com.gerotac.auth.approveanddisapprove.data.remote.dto.response.ResponseApprove
import com.gerotac.auth.updaterequirement.data.remote.dto.request.RequestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto

interface ApproveInterventionRepository {
    suspend fun doApproveIntervention(
        token: String,
        id: Int
    ): ResponseApprove
}