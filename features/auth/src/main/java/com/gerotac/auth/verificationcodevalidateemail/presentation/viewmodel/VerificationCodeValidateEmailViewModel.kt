package com.gerotac.auth.verificationcodevalidateemail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.verificationcodevalidateemail.di.VerificationCodeValidateEmailHeaders
import com.gerotac.auth.verificationcodevalidateemail.domain.usecase.VerificationCodeValidateEmailUseCase
import com.gerotac.auth.verificationcodevalidateemail.presentation.state.VerificationCodeValidateEmailState
import com.gerotac.core.util.UiEvent
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationCodeValidateEmailViewModel @Inject constructor(
    private val verificationCodeValidateEmailUseCase: VerificationCodeValidateEmailUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(VerificationCodeValidateEmailState())
    val state: StateFlow<VerificationCodeValidateEmailState> = _state.asStateFlow()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var makePaymentJob: Job? = null
    fun doVerificationCodeValidateEmail() {
        val token = VerificationCodeValidateEmailHeaders.getHeader()["Authorization"]
        if (makePaymentJob != null) return
        verificationCodeValidateEmailUseCase(token = token!!).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    viewModelScope.launch {
                        _state.update { VerificationCodeValidateEmailState(code = result.data) }
                        _uiEvent.send(UiEvent.Success)
                    }
                }
                is Resource.Error -> {
                    _state.value =
                        VerificationCodeValidateEmailState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    viewModelScope.launch {
                        _uiEvent.send(UiEvent.Error)
                    }
                }
                is Resource.Loading -> {
                    _state.value = VerificationCodeValidateEmailState(true)
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}
