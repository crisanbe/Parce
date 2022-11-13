package com.gerotac.auth.requirement.domain.usecase

import com.gerotac.auth.requirement.domain.model.requirement.RequirementReply
import com.gerotac.auth.requirement.domain.repository.RequirementRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class RequirementUseCase @Inject constructor(
    private val repository: RequirementRepository) {

        operator fun invoke(
            token: String,
            area_intervention: Int,
            description: String,
            cause_problem: String,
            efect_problem: String,
            impact_problem: String,
            file: MutableList<MultipartBody.Part> = mutableListOf()
        ): Flow<Resource<RequirementReply>> {
            return repository.doRequirement(
                token = token,
                area_intervention = area_intervention,
                description = description,
                cause_problem = cause_problem,
                efect_problem = efect_problem,
                impact_problem = impact_problem,
                file = file,
            )
        }
}
