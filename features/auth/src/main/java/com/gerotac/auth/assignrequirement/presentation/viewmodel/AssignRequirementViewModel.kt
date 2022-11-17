package com.gerotac.auth.assignrequirement.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.request.AssignRequest
import com.gerotac.auth.assignrequirement.domain.usecase.AssignRequirementTeacherUseCase
import com.gerotac.auth.assignrequirement.presentation.state.AssignTeacherState
import com.gerotac.auth.codeverificationRegister.di.CodeVerificationHeaders
import com.gerotac.auth.dropdownapi.dropdown.domain.usecase.StudentByAreaUseCase
import com.gerotac.auth.dropdownapi.dropdown.presentation.state.StudentAreaState
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
    private val studentByAreaUseCase: StudentByAreaUseCase

    ) : ViewModel() {

    var state = MutableStateFlow(AssignTeacherState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set
    var stateStudentByArea = MutableStateFlow(StudentAreaState())
        private set
    var query = mutableStateOf("".toInt())

    fun onQueryChanged(query: Int) {
        setQuery(query)
    }

    private fun setQuery(query: Int) {
        this.query.value = query
    }

    init {

    }

    suspend fun assignRequirementTeacher(request: AssignRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            assignRequirementTeacherUseCase(token = token.toString(), request).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { AssignTeacherState(assignTeacher = result.data) }
                        uiEvent.send(UiEvent.Success)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Asignado correctamente👍")
                            )
                        )
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
            }.launchIn(viewModelScope)
        }
    }

    fun doGetStudentByAre(query: Int? = null) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            query?.let {
                studentByAreaUseCase(token = token.toString(), requierementId = it).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            stateStudentByArea.update {
                                StudentAreaState(
                                    studentByAreaState = result.data ?: emptyList()
                                )
                            }
                        }
                        is Resource.Error -> {
                            stateStudentByArea.value = StudentAreaState(false)
                        }
                        is Resource.Loading -> {
                            stateStudentByArea.value = StudentAreaState(true)
                        }
                        else -> Unit
                    }
                }.launchIn(this)
            }
        }
    }
}
