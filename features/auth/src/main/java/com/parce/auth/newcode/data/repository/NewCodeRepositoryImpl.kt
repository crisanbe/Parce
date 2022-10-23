package com.parce.auth.newcode.data.repository

import com.parce.auth.newcode.data.remote.NewCodeApi
import com.parce.auth.newcode.data.remote.dto.ReturnNewCodeDto
import com.parce.auth.newcode.domain.repository.NewCodeRepository
import javax.inject.Inject

class NewCodeRepositoryImpl @Inject constructor(private val api: NewCodeApi) : NewCodeRepository {
    override suspend fun doNewCode(token: String): ReturnNewCodeDto {
        return api.doNewCode(token = token)
    }
}
