package com.parce.auth.login.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.*
import com.parce.auth.login.data.remote.logindto.LoginDto
import com.parce.auth.login.domain.usecase.LoginUseCase
import com.parce.core.util.UiEvent
import com.parce.core.util.UiText
import com.parce.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    var state = MutableStateFlow(LoginState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    var email = MutableLiveData<String>()
        private set
    var password = MutableLiveData<String>()
        private set
    var isLoginEnable = MutableLiveData<Boolean>()
        private set

    suspend fun doLogin(login: LoginDto) {
        viewModelScope.launch {
            loginUseCase(login).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { LoginState(token = result.data) }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = LoginState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = LoginState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

    fun onLoginChanged(Login: LoginDto) {
        email.value = Login.email
        password.value = Login.password
        isLoginEnable.value = enableLogin(LoginDto(Login.email, Login.password))
    }

    private fun enableLogin(Login: LoginDto) =
        Patterns.EMAIL_ADDRESS.matcher(Login.email).matches() && Login.password.length > -0

}
