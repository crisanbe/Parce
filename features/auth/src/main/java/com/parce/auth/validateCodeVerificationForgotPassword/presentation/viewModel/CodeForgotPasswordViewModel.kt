package com.parce.auth.validateCodeVerificationForgotPassword.presentation.viewModel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parce.auth.codeverificationRegister.di.CodeVerificationHeaders
import com.parce.auth.newcode.domain.usecase.NewCodeUseCase
import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.dto.ParameterCodeForgotPasswordDto
import com.parce.auth.validateCodeVerificationForgotPassword.domain.usecase.CodeForgotPasswordUseCase
import com.parce.core.util.UiEvent
import com.parce.shared.network.Resource
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class CodeForgotPasswordViewModel @Inject constructor(
    private val codeForgotPasswordUseCase:
    CodeForgotPasswordUseCase
) :
    ViewModel() {
    private val _state = MutableStateFlow(CodeForgotPasswordState())
    val state: StateFlow<CodeForgotPasswordState> = _state.asStateFlow()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var makePaymentJob: Job? = null
    fun doCodeForgotPassword(token: ParameterCodeForgotPasswordDto) {
        if (makePaymentJob != null) return
        codeForgotPasswordUseCase(token).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    viewModelScope.launch {
                        _state.update { CodeForgotPasswordState() }
                        _uiEvent.send(UiEvent.Success)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        CodeForgotPasswordState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    viewModelScope.launch {
                        _uiEvent.send(UiEvent.Error)
                    }
                }
                is Resource.Loading -> {
                    _state.value = CodeForgotPasswordState(true)
                }
                else -> {}
            }

        }.launchIn(viewModelScope)
    }

}

