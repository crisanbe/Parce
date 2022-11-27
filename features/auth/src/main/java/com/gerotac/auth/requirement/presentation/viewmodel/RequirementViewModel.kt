package com.gerotac.auth.requirement.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class RequirementViewModel @Inject constructor(
    private val requirementUseCase: RequirementUseCase,
    private val getRequirementUseCase: GetRequirementUseCase,
    private val getPaginationUseCase: GetPaginationUseCase
) : ViewModel() {
    private var currentPage = 1
    var state = MutableStateFlow(RequirementState())
        private set
    private var statePages = MutableStateFlow(GetPageState())
    var stateGetRequirement = MutableStateFlow(GetRequirementState(isLoading = true))
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    var query = mutableStateOf("")

    fun onQueryChanged(query: String) {
        setQuery(query)
    }

    private fun setQuery(query: String) {
        this.query.value = query
    }

    init {
        doGetRequirement(query.value, increase = false)
        doGetPagination()
    }

    suspend fun doRequirement(request: RequirementRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            request.file.let {
                requirementUseCase(
                    token = token.toString(),
                    request.area_intervention,
                    request.description,
                    request.cause_problem,
                    request.efect_problem,
                    request.impact_problem,
                    request.file
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            state.update { RequirementState(requirement = result.data) }
                            uiEvent.send(UiEvent.Success)
                        }
                        is Resource.Error -> {
                            state.value = RequirementState(false)
                            uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString(result.message ?: "Error")
                                )
                            )
                            uiEvent.send(UiEvent.Error)
                        }
                        is Resource.Loading -> {
                            state.value = RequirementState(true)
                        }
                        else -> Unit
                    }
                }.launchIn(this)
            }
        }
    }

    fun doGetRequirement(query: String? = null, increase: Boolean? = null) {
        val token = HeaderRequirement.getHeader()["Authorization"]
        viewModelScope.launch {
            if (increase == true) currentPage++ else if (currentPage > 1) currentPage--
            val showPrevious = currentPage > 1
            val showNext = currentPage
            getRequirementUseCase(
                token = token.toString(),
                current_page = currentPage,
                id = query
            ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        stateGetRequirement.update {
                            GetRequirementState(
                                getRequirement = result.data?.data?.result ?: emptyList(),
                                isLoading = false,
                                showPrevious = showPrevious,
                                showNext = showNext < (result.data?.data?.pagination?.last_page
                                    ?: 1)
                            )
                        }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        stateGetRequirement.emit(GetRequirementState(isLoading = false))
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        stateGetRequirement.emit(GetRequirementState(isLoading = true))
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

    fun doGetPagination() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            getPaginationUseCase(
                token = token.toString(),
            ).onEach { resultPage ->
                when (resultPage) {
                    is Resource.Success -> {
                        statePages.update { GetPageState(pagination = resultPage.data) }
                    }
                    is Resource.Error -> {
                        stateGetRequirement.emit(GetRequirementState(isLoading = false))
                    }
                    is Resource.Loading -> {
                        stateGetRequirement.emit(GetRequirementState(isLoading = true))
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}
