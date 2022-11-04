package com.gerotac.auth.codeverificationRegister.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.codeverificationRegister.data.remote.dto.ParameterCodeConfirmationDto
import com.gerotac.auth.codeverificationRegister.di.CodeVerificationHeaders
import com.gerotac.auth.codeverificationRegister.domain.usecase.ConfirmationCodeUseCase
import com.gerotac.core.util.UiEvent
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationCodeViewModel @Inject constructor(private val confirmationCodeUseCase: ConfirmationCodeUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(VerificationCodeState())
    val state: StateFlow<VerificationCodeState> = _state.asStateFlow()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var makePaymentJob: Job? = null
    fun doConfirmationCode(code: ParameterCodeConfirmationDto) {
        val token = CodeVerificationHeaders.getHeader()["Authorization"]
        if (makePaymentJob != null) return
        confirmationCodeUseCase(token = token!!, code = code).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    viewModelScope.launch {
                        _state.update { VerificationCodeState(code = result.data) }
                        _uiEvent.send(UiEvent.Success)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        VerificationCodeState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    viewModelScope.launch {
                        _uiEvent.send(UiEvent.Error)
                    }
                }
                is Resource.Loading -> {
                    _state.value = VerificationCodeState(true)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}
