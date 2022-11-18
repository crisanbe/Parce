package com.gerotac.auth.dropdownapi.dropdown.domain.usecase

import com.gerotac.auth.dropdownapi.dropdown.domain.model.responseacademic.ResultAcademic
import com.gerotac.auth.dropdownapi.dropdown.domain.repository.ApisDropRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AcademicProgramsUseCase @Inject constructor(
    private val repository: ApisDropRepository
    ) {
    operator fun invoke(token: String): Flow<Resource<List<ResultAcademic>>> {
       return repository.getAcademicPrograms(token = token)
    }
}
