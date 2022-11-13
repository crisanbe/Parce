package com.gerotac.auth.requirement.domain.repository

import com.gerotac.auth.requirement.domain.model.detailrequirement.Result
import com.gerotac.shared.network.Resource

interface DetailRequirementRepository {
    suspend fun doDetailRequirement(token: String, id: Int): Resource<Result>
}