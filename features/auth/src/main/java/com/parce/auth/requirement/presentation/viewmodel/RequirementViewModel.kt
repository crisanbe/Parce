package com.parce.auth.requirement.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.auth.requirement.data.remote.requirement.RequirementRequest
import com.parce.auth.requirement.di.HeaderRequirement
import com.parce.auth.requirement.domain.usecase.DetailRequirementUseCase
import com.parce.auth.requirement.domain.usecase.GetPaginationUseCase
import com.parce.auth.requirement.domain.usecase.GetRequirementUseCase
import com.parce.auth.requirement.domain.usecase.RequirementUseCase
import com.parce.auth.requirement.presentation.state.DetailRequirementState
import com.parce.auth.requirement.presentation.state.GetPageState
import com.parce.auth.requirement.presentation.state.GetRequirementState
import com.parce.auth.requirement.presentation.state.RequirementState
import com.parce.auth.updateuser.di.UpdateUserHeaders
import com.parce.core.util.UiEvent
import com.parce.core.util.UiText
import com.parce.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class RequirementViewModel @Inject constructor(
    private val requirementUseCase: RequirementUseCase,
    private val getRequirementUseCase: GetRequirementUseCase,
    private val getPaginationUseCase: GetPaginationUseCase,
    private val getDetailRequirementUseCase: DetailRequirementUseCase
) : ViewModel() {

    private var stateSearch = MutableStateFlow(DetailRequirementState())
    var state = MutableStateFlow(RequirementState())
        private set
    var statePages = MutableStateFlow(GetPageState())
        private set
    var stateGetRequirement by mutableStateOf(GetRequirementState(isLoading = true))
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    var query = mutableStateOf("")

    fun onQueryChanged(query: String) {
        setQuery(query)
    }
    private fun setQuery(query: String){
        this.query.value = query
    }

    init {
        doGetRequirement(increase = false)
        doGetPagination()
    }

    /*fun searchRequirement(query: Int) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            getDetailRequirementUseCase(
                token = token.toString(),
                id = query
            ).also { query->
                when (query) {
                    is Resource.Success -> {
                        stateSearch.update { DetailRequirementState(detailRequirement = query.data) }
                    }
                    is Resource.Error -> {
                        stateSearch.emit(DetailRequirementState(false))
                    }
                    is Resource.Loading -> {
                        stateSearch.emit(DetailRequirementState(true))
                    }
                    else -> Unit
                }
            }
        }
    }*/

    private var currentPage = 1
    private var page = MutableStateFlow(0)

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

    fun doGetRequirement(increase: Boolean? = null, query: String? = null) {
        val token = HeaderRequirement.getHeader()["Authorization"]
        viewModelScope.launch {
            if (increase == true) currentPage++ else if (currentPage > 1) currentPage--
            val showPrevious = currentPage > 1
            val showNext = currentPage
            getRequirementUseCase(
                token = token.toString(),
                current_page = currentPage
            ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        userRepo?.getPage()?.collect { last_pages ->
                            withContext(Dispatchers.Main) {
                                page.value = last_pages
                                page.update { last_pages }
                                stateGetRequirement = stateGetRequirement.copy(
                                    getRequirement = result.data?.data?.result ?: emptyList(),
                                    isLoading = false,
                                    showPrevious = showPrevious,
                                    showNext = showNext < (result.data?.data?.pagination?.last_page
                                        ?: 1)
                                )
                            }
                        }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        stateGetRequirement = stateGetRequirement.copy(isLoading = false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        stateGetRequirement = stateGetRequirement.copy(isLoading = true)
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
                        stateGetRequirement = stateGetRequirement.copy(isLoading = false)
                    }
                    is Resource.Loading -> {
                        stateGetRequirement = stateGetRequirement.copy(isLoading = true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}
