package com.gerotac.auth.requirement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.requirement.domain.repository.DeleteRequirementRepository
import com.gerotac.auth.requirement.domain.usecase.DeleteRequirementCase
import com.gerotac.auth.requirement.presentation.state.DeleteRequirementState
import com.gerotac.auth.updaterequirement.data.remote.dto.request.ResquestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import com.gerotac.auth.updaterequirement.domain.usecase.UpdateRequirementCase
import com.gerotac.auth.updaterequirement.presentation.state.UpdateRequirementState
import com.gerotac.auth.updateuser.di.UpdateUserHeaders
import com.gerotac.auth.updateuser.domain.usecase.UpdateUserCase
import com.gerotac.core.util.UiEvent
import com.gerotac.core.util.UiText
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteRequirementViewModel @Inject constructor(
    private val deleteRequirementCase: DeleteRequirementCase
) :
    ViewModel() {
    var state = MutableStateFlow(DeleteRequirementState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    fun doUpdateRequirement(id: Int) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            deleteRequirementCase(
                token = token!!,
                id = id
            ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update { DeleteRequirementState(deleteRequirement = result.data) }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = DeleteRequirementState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = DeleteRequirementState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}
