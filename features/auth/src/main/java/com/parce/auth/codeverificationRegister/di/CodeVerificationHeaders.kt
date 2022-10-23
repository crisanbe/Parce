package com.parce.auth.codeverificationRegister.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.shared.commons.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object CodeVerificationHeaders : ViewModel() {
    fun getHeader(): Map<String, String> {
        var head = mapOf<String, String>()
        viewModelScope.launch() {
            userRepo?.getTokenRegister()?.collect { tokenRegister ->
                withContext(Dispatchers.Main) {
                    val state = MutableStateFlow(tokenRegister)
                    state.update { tokenRegister }
                    val headers: HashMap<String, String> = hashMapOf(
                        Constant.AUTHORIZATION to "Bearer $tokenRegister",
                        Constant.ACCEPT to "application/json",
                    )
                    head = headers
                }
            }
        }
        return head
    }
}
