package com.gerotac.auth.newpasswordforget.presentation.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.newpasswordforget.data.remote.dto.NewPasswordForgetDto
import com.gerotac.auth.newpasswordforget.domain.usecase.NewPasswordForgetUseCase
import com.gerotac.core.util.UiEvent
import com.gerotac.shared.network.Resource
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class NewPasswordForgetViewModel @Inject constructor(private val newPasswordUseCase: NewPasswordForgetUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(NewPasswordForgetState())
    val state: StateFlow<NewPasswordForgetState> = _state.asStateFlow()
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun doNewPasswordForget(newPasswordForget: NewPasswordForgetDto) {
            newPasswordUseCase(newPasswordForget = newPasswordForget).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        viewModelScope.launch {
                            _state.update { NewPasswordForgetState(newPasswordForgetDto = result.data) }
                            _uiEvent.send(UiEvent.Success)
                        }
                    }
                    is Resource.Error -> {
                        _state.value =
                            NewPasswordForgetState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Error)
                        }
                    }
                    is Resource.Loading -> {
                        _state.value = NewPasswordForgetState(true)
                    }
                    else -> {}
                }

            }.launchIn(viewModelScope)
        }
}
