package com.gerotac.auth.reports.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.domain.usecase.ReportUseCase
import com.gerotac.auth.reports.presentation.statereports.ReportState
import com.gerotac.auth.updateuser.di.UpdateUserHeaders
import com.gerotac.core.util.UiEvent
import com.gerotac.core.util.UiText
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportResponseViewModel @Inject constructor(private val reportUseCase: ReportUseCase) : ViewModel() {
    var state = MutableStateFlow(ReportState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    suspend fun doReport(request: ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
                reportUseCase(
                    token = token.toString(),
                    request
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            state.update { ReportState(message = result.data.toString()) }
                            uiEvent.send(UiEvent.Success)
                        }
                        is Resource.Error -> {
                            state.value = ReportState(false)
                            uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString(result.message ?: "Error")
                                )
                            )
                            uiEvent.send(UiEvent.Error)
                        }
                        is Resource.Loading -> {
                            state.value = ReportState(true)
                        }
                        else -> Unit
                    }
                }.launchIn(this)
        }
    }
}
