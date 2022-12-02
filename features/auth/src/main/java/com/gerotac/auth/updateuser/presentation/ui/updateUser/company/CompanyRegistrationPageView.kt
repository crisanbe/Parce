@file:Suppress("UselessCallOnNotNull")

package com.gerotac.auth.updateuser.presentation.ui.updateUser.company

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gerotac.auth.R
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.ResultX
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.Town
import com.gerotac.auth.dropdownapi.dropdown.presentation.ui.DropLocationDpt
import com.gerotac.auth.dropdownapi.dropdown.presentation.ui.DropLocationMnPo
import com.gerotac.auth.dropdownapi.dropdown.presentation.viewmodel.GetApisDropViewModel
import com.gerotac.auth.login.presentation.components.logincomposables.userDataStore
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.protodata.ProtoUserRepoImpl
import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserRequest
import com.gerotac.auth.updateuser.presentation.viewmodel.UpdateUserViewModel
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.datatime.DataTimeString
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.dropdown.DropString
import com.gerotac.components_ui.componets.progress.LinearProgressBar
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CompanyRegistrationPageView(
    viewModel: UpdateUserViewModel = hiltViewModel(),
    viewModelLocation: GetApisDropViewModel = hiltViewModel(),
    navController: NavController,
    companyName: String?,
    identificationType: String?,
    idNumber: String?,
    companyType: String?,
    kindCompany: String?,
    economicActivity: String?,
    phone: String?
) {
    val context = LocalContext.current
    val eventFlow = viewModel.uiEvent.receiveAsFlow()
    val scaffoldState = rememberScaffoldState()
    val lifecycleTokenScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState()
    val stateMunicipality = viewModelLocation.stateMuni.collectAsState()
    val stateLocation = viewModelLocation.stateLocation.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = Color.White,
                    contentColor = Color.Yellow,
                    snackbarData = data,
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(percent = 20),
                    backgroundColor = Color.Black
                )
            }
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
        ) {
            stateMunicipality.value.muniState.let { municipality ->
                stateLocation.value.locationState.let { department ->
                    CompanyRegistrationPage(
                        navController = navController,
                        municipalityList = municipality,
                        location = department,
                        onClickSave = {
                            lifecycleTokenScope.launch {
                                viewModel.doUpdateUser(
                                    ParameterUpdateUserRequest(
                                        name = if(!companyName.isNullOrEmpty()){companyName}else{""},
                                        type_document = if(!identificationType.isNullOrEmpty()){identificationType}else{""},
                                        document = if(!idNumber.isNullOrEmpty()){idNumber}else{""},
                                        type_bussiness = if(!companyType.isNullOrEmpty()){companyType}else{""},
                                        type_society = kindCompany?.toInt(),
                                        activity_economy = if(!economicActivity.isNullOrEmpty()){economicActivity}else{""},
                                        phone = if(!phone.isNullOrEmpty()){phone}else{""},
                                        person_contact = it[0],
                                        departament = it[1].toInt(),
                                        municipality = it[2].toInt(),
                                        address = it[3],
                                        birthday = it[4],
                                        gener = it[5],
                                        group_etnic = it[6],
                                        presents_disability = it[7]
                                    )
                                )
                                eventFlow.collect { event ->
                                    when (event) {
                                        is UiEvent.Success -> {
                                            userRepo?.saveNameUser(companyName)
                                            navController.navigate(
                                                DrawerScreens.CompanyHome.route
                                                        + "?user=$companyName"
                                            )
                                            scaffoldState.snackbarHostState.showSnackbar(
                                                message = "Se guardo exitosamente🏅",
                                                actionLabel = "Continue"
                                            )
                                        }
                                        is UiEvent.ShowSnackBar -> {
                                            scaffoldState.snackbarHostState.showSnackbar(
                                                message = event.message.asString(context)
                                            )
                                        }
                                        else -> Unit
                                    }
                                }
                            }
                        }
                    )
                }
            }
            LinearProgressBar(state.value.isLoading)
        }
    }
}

@Composable
fun CompanyRegistrationPage(
    navController: NavController,
    onClickSave: (List<String>) -> Unit,
    location: List<ResultX> = emptyList(),
    municipalityList: List<Town> = emptyList()
) {
    var contactPerson by remember { mutableStateOf("") }
    var department by remember { mutableStateOf(0) }
    var municipality by remember { mutableStateOf(0) }
    var address by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var ethnicGroup by remember { mutableStateOf("") }
    var presentsDisability by remember { mutableStateOf("") }
    val rememberToken = remember { mutableStateOf("") }
    userRepo = ProtoUserRepoImpl(LocalContext.current.userDataStore)
    LaunchedEffect(true) {
        userRepo?.getTokenLoginState()?.collect { token ->
            withContext(Dispatchers.Main) {
                rememberToken.value = token
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopPart(onClickAction = { navController.navigate(AppScreens.CompanyRegistration.route) })
        Text(
            text = stringResource(R.string.Text_Company_profile),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 55.dp, top = 5.dp)
        )
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))
            DropLocationDpt(
                selectOptionChange = { department = it },
                text = "Departamento",
                options = location,
                mainIcon = painterResource(id = R.drawable.departament)
            )
            Spacer(Modifier.height(10.dp))
            DropLocationMnPo(
                selectOptionChange = { municipality = it },
                text = "Municipio",
                options = municipalityList,
                mainIcon = null
            )
            TextField(
                value = contactPerson,
                onValueChange = { contactPerson = it },
                label = { Text("Contacto de persona") },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "Contacto de persona",
                    )
                    Divider(
                        modifier = Modifier
                            .width(34.3.dp)
                            .height(30.dp)
                            .padding(start = 33.dp)
                    )
                }
            )
            TextField(
                value = address,
                onValueChange = { address = it },
                label = { Text(stringResource(id = R.string.TextField_Address)) },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = stringResource(id = R.string.TextField_Address),
                    )
                    Divider(
                        modifier = Modifier
                            .width(34.3.dp)
                            .height(30.dp)
                            .padding(start = 33.dp)
                    )
                }
            )
            DataTimeString(dateState = {birthday = it}, value = birthday)
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = {genre = it},
                text = "Tipo de genero",
                options = listOf("Hombre", "Mujer", "Prefiero no decir"),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.genders)
            )
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = {ethnicGroup = it},
                text = "Grupo etnico",
                options = listOf(
                    "Afrocolombiano",
                    "Sin Pertenencia a Grupo",
                    "Mestizo", "Indígena",
                    "Palenquero", "Raizal",
                    "Romo Gitano", "Sin información"
                ),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.groups)
            )
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = {presentsDisability = it},
                text = "Tipo de discapasidad",
                options = listOf("NO", "SI"),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.ic_disability)
            )
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = {
                    onClickSave.invoke(
                        listOf(
                            if(!contactPerson.isNullOrEmpty()){contactPerson}else{""},
                            if(!department.toString().isNullOrEmpty()){department}else{""},
                            if(!municipality.toString().isNullOrEmpty()){municipality}else{""},
                            if(!address.isNullOrEmpty()){address}else{""},
                            if(!birthday.isNullOrEmpty()){birthday}else{""},
                            if(!genre.isNullOrEmpty()){genre}else{""},
                            if(!ethnicGroup.isNullOrEmpty()){ethnicGroup}else{""},
                            if(!presentsDisability.isNullOrEmpty()){presentsDisability}else{""},
                        ) as List<String>
                    )
                },
                shape = RoundedCornerShape(percent = 45),
                modifier = Modifier.size(height = 55.dp, width = 300.dp),
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.Button_Save),
                    modifier = Modifier,
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun CompanyRegistrationPagePreview() {
    CompanyRegistrationPage(navController = rememberNavController(), onClickSave = {})
}
