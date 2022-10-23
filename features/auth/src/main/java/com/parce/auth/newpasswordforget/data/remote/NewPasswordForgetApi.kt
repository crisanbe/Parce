package com.parce.auth.newpasswordforget.data.remote

import com.parce.auth.newpasswordforget.data.remote.dto.NewPasswordForgetDto
import com.parce.auth.newpasswordforget.data.remote.dto.ReturnNewPasswordForgetDto
import retrofit2.http.Body
import retrofit2.http.POST

interface NewPasswordForgetApi {
    @POST("auth/password/reset")
    suspend fun doNewPasswordForget(
        @Body newPasswordForgetDto: NewPasswordForgetDto
    ): ReturnNewPasswordForgetDto
}
