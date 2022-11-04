package com.gerotac.auth.dropdownapi.dropacademicprograms.domain.usecase

import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.locationmodel.Town
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.repository.ApisDropRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MunicipalityUseCase @Inject constructor(
    private val repository: ApisDropRepository) {
    operator fun invoke(token: String): Flow<Resource<List<Town>>> {
       return repository.getMunicipalityList(token = token)
    }
}
