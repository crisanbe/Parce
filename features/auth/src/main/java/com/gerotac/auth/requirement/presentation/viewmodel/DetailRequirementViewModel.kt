package com.gerotac.auth.requirement.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.requirement.domain.usecase.DetailRequirementUseCase
import com.gerotac.auth.requirement.presentation.state.DetailRequirementState
import com.gerotac.auth.updateuser.di.UpdateUserHeaders
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private fun detailRequirement() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        savedStateHandle.get<Int>("id")?.let { characterId ->
            viewModelScope.launch {
                getDetailRequirementUseCase(token = token.toString(), id = characterId).also { query ->
                    state = when (query) {
                        is Resource.Success -> {
                            state.copy(
                                detailRequirement = query.data,
                                fileRequirement = query.data?.relations?.files ?: emptyList(),
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
