package com.gerotac.auth.newcode.domain.repository

import com.gerotac.auth.newcode.data.remote.dto.ReturnNewCodeDto

interface NewCodeRepository {
    suspend fun doNewCode(token: String) : ReturnNewCodeDto
}
