package com.gerotac.auth.intervention.createintervention.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.intervention.createintervention.data.remote.request.SaveInterventionRequest
import com.gerotac.auth.intervention.createintervention.domain.usecase.SaveInterventionUseCase
import com.gerotac.auth.intervention.createintervention.presentation.stateSaveIntervention.SaveInterventionState
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementRequest
import com.gerotac.auth.requirement.di.HeaderRequirement
import com.gerotac.auth.requirement.domain.usecase.GetPaginationUseCase
import com.gerotac.auth.requirement.domain.usecase.GetRequirementUseCase
import com.gerotac.auth.requirement.domain.usecase.RequirementUseCase
import com.gerotac.auth.requirement.presentation.state.GetPageState
import com.gerotac.auth.requirement.presentation.state.GetRequirementState
import com.gerotac.auth.requirement.presentation.state.RequirementState
import com.gerotac.auth.updateuser.di.UpdateUserHeaders
import com.gerotac.core.util.UiEvent
import com.gerotac.core.util.UiText
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class InterventionViewModel @Inject constructor(
    private val saveInterventionUseCase: SaveInterventionUseCase,
) : ViewModel() {
    var state = MutableStateFlow(SaveInterventionState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    suspend fun doSaveIntervention(request: SaveInterventionRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            request.file.let {
                saveInterventionUseCase(
                    token = token.toString(),
                    request.type_intervention,
                    request.description,
                    request.requierement_id,
                    request.file
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            state.update { SaveInterventionState(intervention = result.data) }
                            uiEvent.send(UiEvent.Success)
                        }
                        is Resource.Error -> {
                            state.value = SaveInterventionState(false)
                            uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString(result.message ?: "Error")
                                )
                            )
                            uiEvent.send(UiEvent.Error)
                        }
                        is Resource.Loading -> {
                            state.value = SaveInterventionState(true)
                        }
                        else -> Unit
                    }
                }.launchIn(this)
            }
        }
    }
}
