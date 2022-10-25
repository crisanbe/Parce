package com.parce.auth.requirement.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parce.auth.requirement.domain.usecase.DetailRequirementUseCase
import com.parce.auth.requirement.presentation.state.DetailRequirementState
import com.parce.auth.updateuser.di.UpdateUserHeaders
import com.parce.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRequirementViewModel @Inject constructor(
    private val getDetailRequirementUseCase: DetailRequirementUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(DetailRequirementState())
        private set

    init {
        detailRequirement()
    }

    fun detailRequirement() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        savedStateHandle.get<Int>("id")?.let { characterId ->
            viewModelScope.launch {
                getDetailRequirementUseCase(
                    token = token.toString(),
                    id = characterId
                ).also { query ->
                    when (query) {
                        is Resource.Success -> {
                            state =
                                state.copy(
                                    detailRequirement = query.data,
                                    isLoading = false,
                                )
                        }
                        is Resource.Error -> {
                            state = state.copy(isLoading = false)
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = true)
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}
