package com.gerotac.auth.newcode.presentation.viewmodel

import com.gerotac.auth.newcode.data.remote.dto.ReturnNewCodeDto

data class NewCodeState(
    val isLoading: Boolean = false,
    val newCodeDto: ReturnNewCodeDto? = null,
    val error : String = ""
)