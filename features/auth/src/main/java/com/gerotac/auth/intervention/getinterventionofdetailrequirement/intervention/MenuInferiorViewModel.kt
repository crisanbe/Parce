package com.gerotac.auth.intervention.getinterventionofdetailrequirement.intervention

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MenuInferiorViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(
        ViewState(
            visible = false,
            onShowRequest = ::onShowRequest,
            onDismissRequest = ::onDismissRequest
        )
    )

    val viewState: StateFlow<ViewState> = _viewState

    private fun onShowRequest() {
        viewModelScope.launch {
            _viewState.emit(value = _viewState.value.copy(visible = true))
        }
    }

    private fun onDismissRequest() {
        viewModelScope.launch {
            _viewState.emit(value = _viewState.value.copy(visible = false))
        }
    }

}