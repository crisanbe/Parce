package com.gerotac.auth.newpasswordforget.domain.usecase

import com.gerotac.auth.newpasswordforget.data.remote.dto.NewPasswordForgetDto
import com.gerotac.auth.newpasswordforget.data.remote.dto.ReturnNewPasswordForgetDto
import com.gerotac.auth.newpasswordforget.domain.repository.NewPasswordForgetRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewPasswordForgetUseCase @Inject constructor(private val repository: NewPasswordForgetRepository){
    operator fun invoke(newPasswordForget: NewPasswordForgetDto) : Flow<Resource<ReturnNewPasswordForgetDto>> = flow {
        try {
            emit(Resource.Loading<ReturnNewPasswordForgetDto>())
            val newPasswordForget = repository.doNewPasswordForget(newPasswordForget)
            emit(Resource.Success<ReturnNewPasswordForgetDto>(newPasswordForget))
        }catch (e: HttpException){
            emit(
                Resource.Error<ReturnNewPasswordForgetDto>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        }catch (e: IOException){
            emit(Resource.Error<ReturnNewPasswordForgetDto>("Couldn't reach server. Check you internet connection"))
        }
    }
}
