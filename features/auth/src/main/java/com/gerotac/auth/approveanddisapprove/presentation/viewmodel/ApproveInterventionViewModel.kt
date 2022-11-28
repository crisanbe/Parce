package com.gerotac.auth.approveanddisapprove.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.approveanddisapprove.domain.usecase.ApproveInterventionCase
import com.gerotac.auth.approveanddisapprove.domain.usecase.DisapproveInterventionCase
import com.gerotac.auth.approveanddisapprove.presentation.state.ApproveAndDisapproveInterventionState
import com.gerotac.auth.updateuser.di.UpdateUserHeaders
import com.gerotac.core.util.UiEvent
import com.gerotac.core.util.UiText
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApproveInterventionViewModel @Inject constructor(
    private val approveInterventionCase: ApproveInterventionCase,
    private val disapproveInterventionCase: DisapproveInterventionCase
) :
    ViewModel() {
    var state = MutableStateFlow(ApproveAndDisapproveInterventionState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    fun doApproveIntervention(id: Int) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            approveInterventionCase(
                token = token!!,
                id = id
            ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { ApproveAndDisapproveInterventionState(approveIntervention = result.data) }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = ApproveAndDisapproveInterventionState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = ApproveAndDisapproveInterventionState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

    fun doDisapproveIntervention(id: Int) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            disapproveInterventionCase(
                token = token!!,
                id = id
            ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { ApproveAndDisapproveInterventionState(approveIntervention = result.data) }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = ApproveAndDisapproveInterventionState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = ApproveAndDisapproveInterventionState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}
