package com.parce.auth.updateuser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parce.auth.register.presentation.state.RegisterState
import com.parce.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.parce.auth.updateuser.di.UpdateUserHeaders
import com.parce.auth.updateuser.domain.usecase.UpdateUserCase
import com.parce.core.util.UiEvent
import com.parce.core.util.UiText
import com.parce.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateUserViewModel @Inject constructor(
    private val updateUserCase: UpdateUserCase
) :
    ViewModel() {
    var state = MutableStateFlow(UpdateUserState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    fun doUpdateUser(parameterUpdateUser: ParameterUpdateUserDto) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            updateUserCase(
                token = token!!,
                parameterUpdateUser = parameterUpdateUser
            ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { UpdateUserState(parameterUpdateUser = result.data) }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = UpdateUserState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = UpdateUserState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}
