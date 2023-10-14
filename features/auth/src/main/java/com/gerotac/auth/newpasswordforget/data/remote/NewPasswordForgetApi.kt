package com.gerotac.auth.newpasswordforget.data.remote

import com.gerotac.auth.newpasswordforget.data.remote.dto.NewPasswordForgetDto
import com.gerotac.auth.newpasswordforget.data.remote.dto.ReturnNewPasswordForgetDto
import retrofit2.http.Body
import retrofit2.http.POST

interface NewPasswordForgetApi {
    @POST("auth/password/reset")
    suspend fun doNewPasswordForget(
        @Body newPasswordForgetDto: NewPasswordForgetDto
    ): ReturnNewPasswordForgetDto
}
