package com.parce.auth.newcode.domain.repository

import com.parce.auth.newcode.data.remote.dto.ReturnNewCodeDto

interface NewCodeRepository {
    suspend fun doNewCode(token: String) : ReturnNewCodeDto
}
