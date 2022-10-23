package com.parce.auth.verificationcodevalidateemail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.CodeVerificationTokenDto
import com.parce.auth.verificationcodevalidateemail.di.VerificationCodeValidateEmailHeaders
import com.parce.auth.verificationcodevalidateemail.domain.usecase.ConfirmationCodeTokenUseCase
import com.parce.auth.verificationcodevalidateemail.presentation.state.VerificationCodeTokenState
import com.parce.auth.verificationcodevalidateemail.presentation.state.VerificationCodeValidateEmailState
import com.parce.core.util.UiEvent
import com.parce.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationCodeTokenViewModel @Inject constructor(
    private val confirmationCodeTokenUseCase: ConfirmationCodeTokenUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(VerificationCodeTokenState())
    val state: StateFlow<VerificationCodeTokenState> = _state.asStateFlow()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var makePaymentJob: Job? = null
    fun doConfirmationCodeToken(code: CodeVerificationTokenDto) {
        val token = VerificationCodeValidateEmailHeaders.getHeader()["Authorization"]
        if (makePaymentJob != null) return
        confirmationCodeTokenUseCase(token = token!!, code = code).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    viewModelScope.launch {
                        _state.update { VerificationCodeTokenState(code = result.data) }
                        _uiEvent.send(UiEvent.Success)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        VerificationCodeTokenState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    viewModelScope.launch {
                        _uiEvent.send(UiEvent.Error)
                    }
                }
                is Resource.Loading -> {
                    _state.value = VerificationCodeTokenState(true)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}
