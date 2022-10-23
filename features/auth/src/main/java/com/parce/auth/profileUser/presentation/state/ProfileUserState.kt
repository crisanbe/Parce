package com.parce.auth.profileUser.presentation.state

import com.parce.auth.profileUser.domain.model.User

data class ProfileUserState(
    val isLoading: Boolean = false,
    val profileUserResponse: List<User>? = emptyList(),
    val error: String = "",
    val message: String = ""
)