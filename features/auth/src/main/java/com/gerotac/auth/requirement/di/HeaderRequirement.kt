package com.gerotac.auth.requirement.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.shared.commons.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object HeaderRequirement : ViewModel() {
    fun getHeader(): Map<String, String> {
        var head = mapOf<String, String>()
        viewModelScope.launch() {
            userRepo?.getTokenLoginState()?.collect { tokenLogin ->
                withContext(Dispatchers.Main) {
                    val state = MutableStateFlow(tokenLogin)
                    state.update { tokenLogin }
                    val headers: HashMap<String, String> = hashMapOf(
                        Constant.AUTHORIZATION to "Bearer $tokenLogin",
                        Constant.CONTENT_TYPE to "multipart/form-data; boundary=",
                        Constant.ACCEPT_ENCODING to "gzip, deflate, br",
                        Constant.ACCEPT to "application/json",
                    )
                    head = headers
                }
            }
        }
        return head
    }

    fun getRol(): Map<String, String> {
        var head = mapOf<String, String>()
        viewModelScope.launch() {
            userRepo?.getRolLogin()?.collect { rol ->
                withContext(Dispatchers.Main) {
                    val state = MutableStateFlow(rol)
                    state.update { rol }
                    val headers: HashMap<String, String> = hashMapOf(
                        "rol" to rol,
                    )
                    head = headers
                }
            }
        }
        return head
    }

    fun getNameUser(): Map<String, String> {
        var head = mapOf<String, String>()
        viewModelScope.launch() {
            userRepo?.getNameUser()?.collect { name ->
                withContext(Dispatchers.Main) {
                    val state = MutableStateFlow(name)
                    state.update { name }
                    val headers: HashMap<String, String> = hashMapOf(
                        "name" to name,
                    )
                    head = headers
                }
            }
        }
        return head
    }

    fun getEmailUser(): Map<String, String> {
        var head = mapOf<String, String>()
        viewModelScope.launch() {
            userRepo?.getEmailUser()?.collect { email ->
                withContext(Dispatchers.Main) {
                    val state = MutableStateFlow(email)
                    state.update { email }
                    val headers: HashMap<String, String> = hashMapOf(
                        "email" to email,
                    )
                    head = headers
                }
            }
        }
        return head
    }
}
