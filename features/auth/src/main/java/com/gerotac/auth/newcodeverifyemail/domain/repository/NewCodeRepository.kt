package com.gerotac.auth.newcodeverifyemail.domain.repository

import com.gerotac.auth.newcodeverifyemail.data.remote.dto.ReturnNewCodeDto

interface NewCodeRepository {
    suspend fun doNewCode(token: String) : ReturnNewCodeDto
}
