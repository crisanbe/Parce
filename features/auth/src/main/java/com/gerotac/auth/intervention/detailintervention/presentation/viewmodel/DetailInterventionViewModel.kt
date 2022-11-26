package com.gerotac.auth.intervention.detailintervention.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.intervention.detailintervention.domain.usecase.GetInterventionOfDetailUseCase
import com.gerotac.auth.intervention.detailintervention.presentation.state.DetailInterventionState
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
class DetailInterventionViewModel @Inject constructor(
    private val getInterventionOfDetailUseCase: GetInterventionOfDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(DetailInterventionState(isLoading = true))
        private set

    init {
        detailIntervention()
    }

    fun detailIntervention() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        savedStateHandle.get<Int>("id")?.let { Id ->
            viewModelScope.launch {
                getInterventionOfDetailUseCase(
                    token = token.toString(),
                    id = Id
                ).also { query ->
                    state = when (query) {
                        is Resource.Success -> {
                            state.copy(
                                intervention = query.data,
                                fileIntervention = query.data?.data?.relations?.files ?: emptyList(),
                                isLoading = false,
                            )
                        }
                        is Resource.Error -> {
                            state.copy(isLoading = false)
                        }
                        is Resource.Loading -> {
                            state.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }
}
