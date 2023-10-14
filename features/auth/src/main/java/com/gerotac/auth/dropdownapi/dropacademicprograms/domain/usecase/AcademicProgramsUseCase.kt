package com.gerotac.auth.dropdownapi.dropacademicprograms.domain.usecase

import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.dropmodel.Result
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.repository.ApisDropRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AcademicProgramsUseCase @Inject constructor(
    private val repository: ApisDropRepository) {
    operator fun invoke(token: String): Flow<Resource<List<Result>>> {
       return repository.getAcademicPrograms(token = token)
    }
}
