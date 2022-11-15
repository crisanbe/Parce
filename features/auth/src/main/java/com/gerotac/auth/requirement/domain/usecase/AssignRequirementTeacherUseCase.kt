package com.gerotac.auth.requirement.domain.usecase

import com.gerotac.auth.requirement.domain.model.assignrequirement.response.Assign
import com.gerotac.auth.requirement.domain.repository.AssignTeacherRepository
import com.gerotac.shared.network.Resource
import javax.inject.Inject

class AssignRequirementTeacherUseCase @Inject constructor(
    private val repository: AssignTeacherRepository
) {
    suspend operator fun invoke(
        token: String,
        teacher: List<Int>,
        idRequirement: Int
    ): Resource<Assign> {
        return repository.doAssignTeacher(
            token = token,
            teacher = teacher,
            idRequirement = idRequirement
        )
    }
}
