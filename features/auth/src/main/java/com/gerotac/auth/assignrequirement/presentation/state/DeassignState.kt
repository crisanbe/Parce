package com.gerotac.auth.assignrequirement.presentation.state

import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.response.AssignDto

data class DeassignState(
    val isLoading: Boolean = false,
    val deassign: AssignDto? = null,
    val error: String = "",
    val message: String = ""
)
