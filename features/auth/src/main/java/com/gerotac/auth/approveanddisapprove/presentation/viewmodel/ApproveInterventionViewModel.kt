package com.gerotac.auth.approveanddisapprove.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.approveanddisapprove.domain.usecase.ApproveInterventionCase
import com.gerotac.auth.approveanddisapprove.presentation.state.ApproveInterventionState
import com.gerotac.auth.updaterequirement.data.remote.dto.request.RequestUpdateRequirement
import com.gerotac.auth.updaterequirement.domain.usecase.UpdateRequirementCase
import com.gerotac.auth.updaterequirement.presentation.state.UpdateRequirementState
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
    private val approveInterventionCase: ApproveInterventionCase
) :
    ViewModel() {
    var state = MutableStateFlow(ApproveInterventionState())
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
                        state.update { ApproveInterventionState(approveIntervention = result.data) }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = ApproveInterventionState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = ApproveInterventionState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}
