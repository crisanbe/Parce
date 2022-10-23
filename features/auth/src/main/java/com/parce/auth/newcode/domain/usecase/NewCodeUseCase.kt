package com.parce.auth.newcode.domain.usecase

import com.parce.auth.newcode.data.remote.dto.ReturnNewCodeDto
import com.parce.auth.newcode.domain.repository.NewCodeRepository
import com.parce.shared.network.Resource
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewCodeUseCase @Inject constructor(private val repository: NewCodeRepository) {
    operator fun invoke(token: String): Flow<Resource<ReturnNewCodeDto>> = flow {
        try {
            emit(Resource.Loading<ReturnNewCodeDto>())
            val newCode = repository.doNewCode(token = token)
            emit(Resource.Success<ReturnNewCodeDto>(newCode))
        } catch (e: HttpException) {
            emit(
                Resource.Error<ReturnNewCodeDto>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<ReturnNewCodeDto>("Couldn't reach server. Check you internet connection"))
        }
    }
}
