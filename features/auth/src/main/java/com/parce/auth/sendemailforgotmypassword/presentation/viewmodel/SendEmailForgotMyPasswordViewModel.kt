package com.parce.auth.sendemailforgotmypassword.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parce.auth.sendemailforgotmypassword.data.remote.dto.SendEmailForgotMyPasswordDto
import com.parce.auth.sendemailforgotmypassword.domain.usecase.SendEmailForgotMyPasswordUseCase
import com.parce.core.util.UiEvent
import com.parce.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendEmailForgotMyPasswordViewModel @Inject constructor(
    private val resendNewCodeUseCase: SendEmailForgotMyPasswordUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(SendEmailForgotMyPasswordState())
    val state: StateFlow<SendEmailForgotMyPasswordState> = _state.asStateFlow()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var makePaymentJob: Job? = null
    fun doResendNewCode(email: SendEmailForgotMyPasswordDto) {
        if (makePaymentJob != null) return
        resendNewCodeUseCase(email = email).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    viewModelScope.launch {
                        _state.update { SendEmailForgotMyPasswordState(resendNewCode = result.data) }
                        _uiEvent.send(UiEvent.Success)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        SendEmailForgotMyPasswordState(
                            error = result.message ?: "An unexpected error ocurred"
                        )
                    viewModelScope.launch {
                        _uiEvent.send(UiEvent.Error)
                    }
                }
                is Resource.Loading -> {
                    _state.value = SendEmailForgotMyPasswordState(true)
                }
                else -> {}
            }

        }.launchIn(viewModelScope)
    }
}
