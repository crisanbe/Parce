package com.gerotac.auth.dropdownapi.dropdown.domain.usecase

import com.gerotac.auth.dropdownapi.dropdown.domain.model.listateacher.ResultTeacher
import com.gerotac.auth.dropdownapi.dropdown.domain.repository.ApisDropRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListTeacherUseCase @Inject constructor(
    private val repository: ApisDropRepository) {
    operator fun invoke(token: String): Flow<Resource<List<ResultTeacher>>> {
       return repository.getTeacherList(token = token)
    }
}
