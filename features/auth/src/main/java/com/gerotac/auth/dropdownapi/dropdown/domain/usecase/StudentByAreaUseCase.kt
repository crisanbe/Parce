package com.gerotac.auth.dropdownapi.dropdown.domain.usecase

import com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea.ResultStudent
import com.gerotac.auth.dropdownapi.dropdown.domain.repository.ApisDropRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentByAreaUseCase @Inject constructor(
    private val repository: ApisDropRepository
) {
    operator fun invoke(token: String, requierementId: Int): Flow<Resource<List<ResultStudent>>> {
        return repository.getStudentByArea(token = token, requierementId = requierementId)
    }
}
