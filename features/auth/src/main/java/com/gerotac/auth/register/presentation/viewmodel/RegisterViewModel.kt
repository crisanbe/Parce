package com.gerotac.auth.register.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.register.data.remote.dto.RegisterDto
import com.gerotac.auth.register.domain.usecase.RegisterUseCase
import com.gerotac.auth.register.presentation.state.RegisterState
import com.gerotac.core.util.UiEvent
import com.gerotac.core.util.UiText
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) :
    ViewModel() {
    var state = MutableStateFlow(RegisterState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    fun doRegister(register: RegisterDto) {
        viewModelScope.launch {
            registerUseCase(register).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { RegisterState(token = result.data) }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = RegisterState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = RegisterState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}
