package com.gerotac.auth.sendemailforgotmypassword.presentation.viewmodel

import com.gerotac.auth.sendemailforgotmypassword.data.remote.dto.RetuntResendNewCodeDto

data class SendEmailForgotMyPasswordState(
    val isLoading: Boolean = false,
    val resendNewCode: RetuntResendNewCodeDto? = null,
    val error: String = ""
)
