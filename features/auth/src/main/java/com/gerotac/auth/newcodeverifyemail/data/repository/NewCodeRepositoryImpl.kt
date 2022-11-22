package com.gerotac.auth.newcodeverifyemail.data.repository

import com.gerotac.auth.newcodeverifyemail.data.remote.NewCodeApi
import com.gerotac.auth.newcodeverifyemail.data.remote.dto.ReturnNewCodeDto
import com.gerotac.auth.newcodeverifyemail.domain.repository.NewCodeRepository
import javax.inject.Inject

class NewCodeRepositoryImpl @Inject constructor(private val api: NewCodeApi) : NewCodeRepository {
    override suspend fun doNewCode(token: String): ReturnNewCodeDto {
        return api.doNewCode(token = token)
    }
}
