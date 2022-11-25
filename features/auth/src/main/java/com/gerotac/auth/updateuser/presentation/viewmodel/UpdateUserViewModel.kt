package com.gerotac.auth.updateuser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserRequest
import com.gerotac.auth.updateuser.di.UpdateUserHeaders
import com.gerotac.auth.updateuser.domain.usecase.UpdateUserCase
import com.gerotac.core.util.UiEvent
import com.gerotac.core.util.UiText
import com.gerotac.shared.network.Resource
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

    fun doUpdateUser(parameterUpdateUser: ParameterUpdateUserRequest) {
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
