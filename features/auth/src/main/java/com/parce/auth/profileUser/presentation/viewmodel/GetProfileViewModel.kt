package com.parce.auth.profileUser.presentation.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parce.auth.profileUser.domain.usecase.ProfileUserUseCase
import com.parce.auth.profileUser.presentation.state.ProfileUserState
import com.parce.auth.updateuser.di.UpdateUserHeaders
import com.parce.core.util.UiEvent
import com.parce.core.util.UiText
import com.parce.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetProfileViewModel @Inject constructor(private val getProfileUseCase: ProfileUserUseCase) :
    ViewModel() {
    var state = MutableStateFlow(ProfileUserState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    init {
        viewModelScope.launch(Dispatchers.Main) {
            doGetProfileUser()
        }
    }

    private suspend fun doGetProfileUser() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            getProfileUseCase(token = token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { ProfileUserState(profileUserResponse = result.data) }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = ProfileUserState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = ProfileUserState(true)
                    }
                    else -> {}
                }
            }.launchIn(this)
        }
    }
}

