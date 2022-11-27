package com.gerotac.core.util

sealed class UiEvent {
    object Success : UiEvent()
    object Error : UiEvent()
    object NavigateUp : UiEvent()
    data class ShowSnackBar(val message: UiText) : UiEvent()
    data class ErrorOkHttpClient(val message: UiText) : UiEvent()
}
