package com.gerotac.auth.approveanddisapprove.data.repository

import com.gerotac.auth.approveanddisapprove.data.remote.api.ApproveInterventionApi
import com.gerotac.auth.approveanddisapprove.data.remote.dto.response.ResponseApprove
import com.gerotac.auth.approveanddisapprove.domain.repository.ApproveInterventionRepository
import javax.inject.Inject

class ApproveInterventionRepositoryImpl @Inject constructor(
    private val api: ApproveInterventionApi) :
    ApproveInterventionRepository {
    override suspend fun doApproveIntervention(
        token: String,
        id: Int
    ): ResponseApprove {
        return api.doApproveIntervention(token = token, id)
    }
}
