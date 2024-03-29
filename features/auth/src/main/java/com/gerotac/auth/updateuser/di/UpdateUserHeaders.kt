package com.gerotac.auth.updateuser.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.shared.commons.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object UpdateUserHeaders : ViewModel() {
    fun getHeader(): Map<String, String> {
        var head = mapOf<String, String>()
        viewModelScope.launch() {
            userRepo?.getTokenLoginState()?.collect { tokenLogin ->
                withContext(Dispatchers.Main) {
                    val state = MutableStateFlow(tokenLogin)
                    state.update { tokenLogin }
                    val headers: HashMap<String, String> = hashMapOf(
                        Constant.AUTHORIZATION to "Bearer $tokenLogin",
                        Constant.ACCEPT to "application/json",
                    )
                    head = headers
                }
            }
        }
        return head
    }
}
