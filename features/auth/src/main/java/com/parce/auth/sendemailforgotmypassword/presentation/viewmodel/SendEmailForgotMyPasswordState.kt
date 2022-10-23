package com.parce.auth.sendemailforgotmypassword.presentation.viewmodel

import com.parce.auth.sendemailforgotmypassword.data.remote.dto.RetuntResendNewCodeDto

data class SendEmailForgotMyPasswordState(
    val isLoading: Boolean = false,
    val resendNewCode: RetuntResendNewCodeDto? = null,
    val error: String = ""
)
