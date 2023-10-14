package com.gerotac.auth.newcode.data.repository

import com.gerotac.auth.newcode.data.remote.NewCodeApi
import com.gerotac.auth.newcode.data.remote.dto.ReturnNewCodeDto
import com.gerotac.auth.newcode.domain.repository.NewCodeRepository
import javax.inject.Inject

class NewCodeRepositoryImpl @Inject constructor(private val api: NewCodeApi) : NewCodeRepository {
    override suspend fun doNewCode(token: String): ReturnNewCodeDto {
        return api.doNewCode(token = token)
    }
}
