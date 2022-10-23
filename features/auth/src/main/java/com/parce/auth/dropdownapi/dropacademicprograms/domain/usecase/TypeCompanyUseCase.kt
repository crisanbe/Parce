package com.parce.auth.dropdownapi.dropacademicprograms.domain.usecase

import com.parce.auth.dropdownapi.dropacademicprograms.domain.model.dropmodel.Result
import com.parce.auth.dropdownapi.dropacademicprograms.domain.repository.ApisDropRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TypeCompanyUseCase @Inject constructor(
    private val repository: ApisDropRepository) {
    operator fun invoke(token: String): Flow<Resource<List<Result>>> {
       return repository.getTypeCompany(token = token)
    }
}
