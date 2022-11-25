package com.gerotac.auth.intervention.getintervention.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.intervention.getintervention.domain.usecase.GetInterventionOfDetailUseCase
import com.gerotac.auth.intervention.getintervention.presentation.state.DetailInterventionState
import com.gerotac.auth.updateuser.di.UpdateUserHeaders
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRequirementOfInterventionViewModel @Inject constructor(
    private val getInterventionOfDetailUseCase: GetInterventionOfDetailUseCase
) : ViewModel() {

    var state by mutableStateOf(DetailInterventionState(isLoading = true))
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getInterventionOfDetailRequirement()
    }

    private fun getInterventionOfDetailRequirement() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            getInterventionOfDetailUseCase(token = token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(
                            intervention = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                result.message ?: "Unknown error"
                            )
                        )
                    }
                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}