package com.parce.auth.sendemailforgotmypassword.domain.model

data class ReturnSendEmailForgotMyPassword(
    val message: String,
    val state: Boolean,
    val status: String
)
