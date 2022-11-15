package com.gerotac.auth.requirement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.requirement.domain.model.assignrequirement.request.AssignRequest
import com.gerotac.auth.requirement.domain.usecase.AssignRequirementTeacherUseCase
import com.gerotac.auth.requirement.presentation.state.AssignTeacherState
import com.gerotac.auth.requirement.presentation.state.RequirementState
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
    private val assignRequirementTeacherUseCase: AssignRequirementTeacherUseCase
) : ViewModel() {

    var state = MutableStateFlow(AssignTeacherState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    suspend fun assignRequirementTeacher(request: AssignRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            assignRequirementTeacherUseCase(
                token = token.toString(),
                request.userTeacher,
                request.idRequirement
            ).also { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { AssignTeacherState(assignTeacher = result.data) }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = AssignTeacherState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = AssignTeacherState(true)
                    }
                    else -> Unit
                }
            }
        }
    }
}
