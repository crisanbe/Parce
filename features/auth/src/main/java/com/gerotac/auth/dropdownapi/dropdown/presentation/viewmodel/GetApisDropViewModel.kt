package com.gerotac.auth.dropdownapi.dropdown.presentation.viewmodel;

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.dropdownapi.dropdown.domain.usecase.*
import com.gerotac.auth.dropdownapi.dropdown.presentation.state.*
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
    private val listTeacherUseCase: ListTeacherUseCase,
    private val listAreaInterventionUseCase: AreaInterventionUseCase,
    private val studentByAreaUseCase: StudentByAreaUseCase
) :
    ViewModel() {
    var stateAcademic = MutableStateFlow(AcademicProgramsState())
        private set
    var stateCompany = MutableStateFlow(CompanyState())
        private set
    var stateLocation = MutableStateFlow(LocationState())
        private set
    var stateMuni = MutableStateFlow(MuniState())
        private set
    var stateTeacher = MutableStateFlow(ListTeacherState())
        private set
    var stateArea = MutableStateFlow(ListAreaState())
        private set
    var stateStudentByArea = MutableStateFlow(StudentAreaState())
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    /*var query = mutableStateOf("1".toInt())

    fun onQueryChanged(query: Int) {
        setQuery(query)
    }

    private fun setQuery(query: Int) {
        this.query.value = query
    }
*/
    init {
        doAcademicPrograms()
        doTypeCompany()
        doLocation()
        doMunicipality()
        doGetTeacher()
        doGetAreas()
    }

    private fun doAcademicPrograms() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            academicProgramsUseCase(token = token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        stateAcademic.update {
                            AcademicProgramsState(
                                academicProgramsState = result.data ?: emptyList()
                            )
                        }
                        uiEvent.send(UiEvent.Success)
                    }
                    is Resource.Error -> {
                        stateAcademic.value = AcademicProgramsState(false)
                        uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.DynamicString(result.message ?: "Error")
                            )
                        )
                        uiEvent.send(UiEvent.Error)
                    }
                    is Resource.Loading -> {
                        stateAcademic.value = AcademicProgramsState(true)
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
                        stateCompany.update {
                            CompanyState(
                                companyState = result.data ?: emptyList()
                            )
                        }
                    }
                    is Resource.Error -> {
                        stateCompany.value = CompanyState(false)
                    }
                    is Resource.Loading -> {
                        stateCompany.value = CompanyState(true)
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

    private fun doGetTeacher() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            listTeacherUseCase(token = token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        stateTeacher.update {
                            ListTeacherState(
                                teacherState = (result.data ?: emptyList())
                            )
                        }
                    }
                    is Resource.Error -> {
                        stateTeacher.value = ListTeacherState(false)
                    }
                    is Resource.Loading -> {
                        stateTeacher.value = ListTeacherState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

    private fun doGetAreas() {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch {
            listAreaInterventionUseCase(token = token.toString()).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        stateArea.update {
                            ListAreaState(
                                areaState = result.data ?: emptyList()
                            )
                        }
                    }
                    is Resource.Error -> {
                        stateArea.value = ListAreaState(false)
                    }
                    is Resource.Loading -> {
                        stateArea.value = ListAreaState(true)
                    }
                    else -> Unit
                }
            }.launchIn(this)
        }
    }

    fun doGetStudentByArea(query: Int?) {
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

