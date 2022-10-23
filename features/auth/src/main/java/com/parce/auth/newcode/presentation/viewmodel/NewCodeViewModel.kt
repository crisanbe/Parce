package com.parce.auth.newcode.presentation.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parce.auth.codeverificationRegister.di.CodeVerificationHeaders
import com.parce.auth.newcode.domain.usecase.NewCodeUseCase
import com.parce.core.util.UiEvent
import com.parce.shared.network.Resource
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class NewCodeViewModel @Inject constructor(private val newCodeUseCase: NewCodeUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(NewCodeState())
    val state: StateFlow<NewCodeState> = _state.asStateFlow()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private var makePaymentJob: Job? = null
    fun doNewCode() {
        val token = CodeVerificationHeaders.getHeader()["Authorization"]
        if (makePaymentJob != null) return
            newCodeUseCase(token = token!!).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        viewModelScope.launch {
                            _state.update { NewCodeState() }
                            _uiEvent.send(UiEvent.Success)
                        }
                    }
                    is Resource.Error -> {
                        _state.value =
                            NewCodeState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Error)
                        }
                    }
                    is Resource.Loading -> {
                        _state.value = NewCodeState(true)
                    }
                    else -> {}
                }

            }.launchIn(viewModelScope)
        }

}

