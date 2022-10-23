package com.parce.auth.newcode.presentation.viewmodel

import com.parce.auth.newcode.data.remote.dto.ReturnNewCodeDto

data class NewCodeState(
    val isLoading: Boolean = false,
    val newCodeDto: ReturnNewCodeDto? = null,
    val error : String = ""
)