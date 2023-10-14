package com.gerotac.auth.dropdownapi.dropacademicprograms.presentation.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.usecase.AcademicProgramsUseCase
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.usecase.LocationUseCase
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.usecase.MunicipalityUseCase
import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.usecase.TypeCompanyUseCase
import com.gerotac.auth.dropdownapi.dropacademicprograms.presentation.state.AcademicProgramsState
import com.gerotac.auth.dropdownapi.dropacademicprograms.presentation.state.LocationState
import com.gerotac.auth.dropdownapi.dropacademicprograms.presentation.state.MuniState
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
class GetApisDropViewModel @Inject constructor(
    private val academicProgramsUseCase: AcademicProgramsUseCase,
    private val typeCompanyUseCase: TypeCompanyUseCase,
    private val locationUseCase: LocationUseCase,
    private val municipalityUseCase: MunicipalityUseCase,
) :
    ViewModel() {
    var state = MutableStateFlow(AcademicProgramsState())
        private set
    var stateLocation = MutableStateFlow(LocationState())
        private set
    var stateMuni = MutableStateFlow(MuniState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    init {
        doAcademicPrograms()
        doTypeCompany()
        doLocation()
        doMunicipality()
    }

    private fun doAcademicPrograms() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            academicProgramsUseCase(token = token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update {
                            AcademicProgramsState(
                                academicProgramsState = result.data ?: emptyList()
                            )
                        }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        state.value = AcademicProgramsState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        state.value = AcademicProgramsState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

    private fun doTypeCompany() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            typeCompanyUseCase(token = token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        state.update {
                            AcademicProgramsState(
                                academicProgramsState = result.data ?: emptyList()
                            )
                        }
                    }
                    is Resource.Error -> {
                        state.value = AcademicProgramsState(false)
                    }
                    is Resource.Loading -> {
                        state.value = AcademicProgramsState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

    private fun doLocation() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            locationUseCase(token = token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        stateLocation.update {
                            LocationState(
                                locationState = result.data ?: emptyList()
                            )
                        }
                    }
                    is Resource.Error -> {
                        stateLocation.value = LocationState(false)
                    }
                    is Resource.Loading -> {
                        stateLocation.value = LocationState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

    private fun doMunicipality() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            municipalityUseCase(token = token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        stateMuni.update {
                            MuniState(
                                muniState = (result.data ?: emptyList())
                            )
                        }
                    }
                    is Resource.Error -> {
                        stateMuni.value = MuniState(false)
                    }
                    is Resource.Loading -> {
                        stateMuni.value = MuniState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }
}

