package com.parce.auth.requirement.domain.usecase

import com.parce.auth.requirement.data.remote.getdetailrequirement.GetDetailResponse
import com.parce.auth.requirement.domain.model.requirement.RequirementReply
import com.parce.auth.requirement.domain.repository.DetailRequirementRepository
import com.parce.auth.requirement.domain.repository.RequirementRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class DetailRequirementUseCase @Inject constructor(
    private val repository: DetailRequirementRepository
) {

    operator fun invoke(
        token: String,
        id: Int
    ): Flow<Resource<GetDetailResponse>> {
        return repository.doDetailRequirement(
            token = token,
            id = id
        )
    }
}
