package com.gerotac.auth.verificationcodevalidateemail.domain.model

data class ReturnVerificationCodeValidateEmail(
    val message: String,
    val state: Boolean,
    val status: String
)


