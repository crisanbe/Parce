package com.gerotac.auth.newcodeverifyemail.presentation.viewmodel

import com.gerotac.auth.newcodeverifyemail.data.remote.dto.ReturnNewCodeDto

data class NewCodeState(
    val isLoading: Boolean = false,
    val newCodeDto: ReturnNewCodeDto? = null,
    val error : String = ""
)