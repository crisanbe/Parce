package com.gerotac.auth.assignrequirement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.request.AssignRequest
import com.gerotac.auth.assignrequirement.domain.usecase.AssignRequirementStudentUseCase
import com.gerotac.auth.assignrequirement.domain.usecase.AssignRequirementTeacherUseCase
import com.gerotac.auth.assignrequirement.domain.usecase.DeassignRequirementTeacherUseCase
import com.gerotac.auth.assignrequirement.presentation.state.AssignState
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
class AssignRequirementViewModel @Inject constructor(
    private val assignRequirementTeacherUseCase: AssignRequirementTeacherUseCase,
    private val assignRequirementStudentUseCase: AssignRequirementStudentUseCase,
    private val deassignRequirementTeacherUseCase: DeassignRequirementTeacherUseCase

    ) : ViewModel() {

    var state = MutableStateFlow(AssignState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    suspend fun assignRequirementTeacher(request: AssignRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            assignRequirementTeacherUseCase(token = token.toString(), request).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { AssignState(assign = result.data) }
                        uiEvent.send(UiEvent.Success)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Asignado correctamente👍")
                            )
                        )
                    }
                    is Resource.Error -> {
                        state.value = AssignState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = AssignState(true)
                    }
                    else -> Unit
                }
            }.launchIn(viewModelScope)
        }
    }

    suspend fun assignRequirementStudent(request: AssignRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            assignRequirementStudentUseCase(token = token.toString(), request).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { AssignState(assign = result.data) }
                        uiEvent.send(UiEvent.Success)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Asignado correctamente👍")
                            )
                        )
                    }
                    is Resource.Error -> {
                        state.value = AssignState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = AssignState(true)
                    }
                    else -> Unit
                }
            }.launchIn(viewModelScope)
        }
    }

    suspend fun deassignRequirementStudent(request: AssignRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            deassignRequirementTeacherUseCase(token = token.toString(), request).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { AssignState(assign = result.data) }
                        uiEvent.send(UiEvent.Success)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Asignado correctamente👍")
                            )
                        )
                    }
                    is Resource.Error -> {
                        state.value = AssignState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = AssignState(true)
                    }
                    else -> Unit
                }
            }.launchIn(viewModelScope)
        }
    }
}
