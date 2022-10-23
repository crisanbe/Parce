package com.parce.auth.updateuser.presentation.ui.updateintohome.company

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parce.auth.R
import com.parce.auth.dropdownapi.dropacademicprograms.domain.model.dropmodel.Result
import com.parce.auth.dropdownapi.dropacademicprograms.domain.model.locationmodel.ResultX
import com.parce.auth.dropdownapi.dropacademicprograms.domain.model.locationmodel.Town
import com.parce.auth.dropdownapi.dropacademicprograms.presentation.ui.DropLocationDpt
import com.parce.auth.dropdownapi.dropacademicprograms.presentation.ui.DropLocationMnPo
import com.parce.auth.dropdownapi.dropacademicprograms.presentation.viewmodel.GetApisDropViewModel
import com.parce.auth.login.presentation.components.logincomposables.userDataStore
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.auth.protodata.ProtoUserRepoImpl
import com.parce.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.parce.auth.updateuser.presentation.viewmodel.UpdateUserViewModel
import com.parce.components_ui.componets.CircularIndeterminateProgressBar
import com.parce.components_ui.componets.DividerIcon
import com.parce.components_ui.componets.TopBar
import com.parce.components_ui.componets.datatime.DataTimeString
import com.parce.components_ui.componets.drawer.Drawer
import com.parce.components_ui.componets.drawer.DrawerScreens
import com.parce.components_ui.componets.dropdown.DropString
import com.parce.core.util.UiEvent
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UpdateCompanyView(
    viewModel: UpdateUserViewModel = hiltViewModel(),
    viewModelLocation: GetApisDropViewModel = hiltViewModel(),
    navController: NavController,
    title: DrawerScreens,
    scaffoldState: ScaffoldState,
    onClickIconButton: (ScaffoldState) -> Unit,
    onClickDestination: (screen: String) -> Unit,
    companyName: String?,
    identificationType: String?,
    idNumber: String?,
    companyType: String?,
    kindCompany: Int?,
    economicActivity: String?,
    contactPerson: String?,
    department: Int?,
    municipality: Int?,
    academicProgram: String?,
    birthday: String?,
    gene: String?,
    phone: String?,
    ethnicGroup: String?,
    presentsDisability: String?,
    address: String?
) {
    val context = LocalContext.current
    val eventFlow = viewModel.uiEvent.receiveAsFlow()
    val lifecycleTokenScope = rememberCoroutineScope()
    val stateMunicipality = viewModelLocation.stateMuni.collectAsState()
    val stateLocation = viewModelLocation.stateLocation.collectAsState()
    val state = viewModel.state.collectAsState()
    var visible by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(tween(300)),
        exit = slideOutHorizontally()
    ) {
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
            topBar = {
                TopBar(
                    title = title.title,
                    buttonIcon = Icons.Outlined.Menu,
                    scaffoldState = scaffoldState,
                    onClickIconButton = { scaffoldState ->
                        onClickIconButton(scaffoldState)
                    }
                )
            },
            drawerContent = {
                Drawer(
                    navController = navController,
                    onClickDestination = { screen ->
                        onClickDestination(screen)
                    }
                )
            },
            floatingActionButton = {}
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                stateMunicipality.value.muniState.let { municipalityList ->
                    stateLocation.value.locationState.let { departmentList ->
                        UpdateCompany(
                            location = departmentList,
                            municipalityList = municipalityList,
                            companyName = companyName,
                            identificationType = identificationType,
                            idNumber = idNumber,
                            companyType = companyType,
                            kindCompany = kindCompany,
                            economicActivity = economicActivity,
                            contactPerson = contactPerson,
                            department = department,
                            municipality = municipality,
                            academicProgram = academicProgram,
                            birthday = birthday,
                            gene = gene,
                            phone = phone,
                            ethnicGroup = ethnicGroup,
                            presentsDisability = presentsDisability,
                            address = address,
                            navController = navController,
                            onClickSave = {
                                lifecycleTokenScope.launch {
                                    viewModel.doUpdateUser(
                                        ParameterUpdateUserDto(
                                            name = it[0],
                                            type_document = it[1],
                                            document = it[2],
                                            type_bussiness = it[3],
                                            type_society = it[4].toInt(),
                                            activity_economy = it[5],
                                            phone = it[6],
                                            person_contact = it[7],
                                            departament = it[8].toInt(),
                                            municipality = it[9].toInt(),
                                            address = it[10],
                                            birthday = it[11],
                                            gener = it[12],
                                            group_etnic = it[13],
                                            presents_disability = it[14]
                                        )
                                    )
                                    eventFlow.collect { event ->
                                        when (event) {
                                            is UiEvent.Success -> {
                                                navController.navigate(
                                                    DrawerScreens.CompanyProfile.route
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
            }
        }
        CircularIndeterminateProgressBar(state.value.isLoading)
    }
    LaunchedEffect(visible) {
        visible = true
    }
}

@Composable
fun UpdateCompany(
    navController: NavController,
    onClickSave: (List<String>) -> Unit,
    location: List<ResultX> = emptyList(),
    municipalityList: List<Town> = emptyList(),
    companyName: String?,
    identificationType: String?,
    idNumber: String?,
    companyType: String?,
    kindCompany: Int?,
    economicActivity: String?,
    contactPerson: String?,
    department: Int?,
    municipality: Int?,
    academicProgram: String?,
    birthday: String?,
    gene: String?,
    phone: String?,
    ethnicGroup: String?,
    presentsDisability: String?,
    address: String?
) {
    var companyName by remember { mutableStateOf(companyName) }
    var identificationType by remember { mutableStateOf(identificationType) }
    var idNumber by remember { mutableStateOf(idNumber) }
    var companyType by remember { mutableStateOf(companyType) }
    var kindCompany by remember { mutableStateOf(kindCompany) }
    var economicActivity by remember { mutableStateOf(economicActivity) }
    var phone by remember { mutableStateOf(phone) }

    var contactPerson by remember { mutableStateOf(contactPerson) }
    var department by remember { mutableStateOf(department) }
    var municipality by remember { mutableStateOf(municipality) }
    var address by remember { mutableStateOf(address) }
    var academicProgram by remember { mutableStateOf(academicProgram) }
    var birthday by remember { mutableStateOf(birthday) }
    var gene by remember { mutableStateOf(gene) }
    var ethnicGroup by remember { mutableStateOf(ethnicGroup) }
    var presentsDisability by remember { mutableStateOf(presentsDisability) }
    userRepo = ProtoUserRepoImpl(LocalContext.current.userDataStore)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
            Spacer(Modifier.height(12.dp))
            TextField(
                value = companyName.toString(),
                onValueChange = { companyName = it },
                label = { Text(stringResource(id = R.string.TextField_Company_name)) },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = stringResource(id = R.string.TextField_Company_name),
                    )
                    DividerIcon()
                }
            )
            TextField(
                value = economicActivity.toString(),
                onValueChange = { economicActivity = it },
                label = { Text(stringResource(id = R.string.TextField_Economic_activity)) },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.RealEstateAgent,
                        contentDescription = stringResource(id = R.string.TextField_Economic_activity),
                    )
                    DividerIcon()
                }
            )
            TextField(
                value = address.toString(),
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
            Spacer(Modifier.height(5.dp))
            TextField(
                value = contactPerson.toString(),
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
                value = phone.toString(),
                onValueChange = { phone = it },
                label = { Text(stringResource(id = R.string.TextField_Phone)) },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.PhoneAndroid,
                        contentDescription = stringResource(id = R.string.TextField_Phone),
                    )
                    DividerIcon()
                }
            )
            TextField(
                value = idNumber.toString(),
                onValueChange = { idNumber = it },
                label = { Text(stringResource(id = R.string.TextField_Id_number)) },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Pin,
                        contentDescription = stringResource(id = R.string.TextField_Id_number),
                    )
                    DividerIcon()
                }
            )
            DropString(
                ValueState = { identificationType = it },
                text = identificationType.toString(),
                options = listOf("NIT", "Cedula", "Pasaporte", "Cédula de Extranjería"),
                mainIcon = painterResource(id = com.parce.components_ui.R.drawable.identity)
            )
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = { companyType = it },
                text = companyType.toString(),
                options = listOf("Grande", "Mediana", "Pequeña", "Micro"),
                mainIcon = painterResource(id = R.drawable.company)
            )
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = { kindCompany = it.toInt() },
                text = kindCompany.toString(),
                options = listOf(),
                mainIcon = null
            )
            Spacer(Modifier.height(5.dp))
            DropLocationDpt(
                selectOptionChange = { department = it },
                text = "Departamento",
                options = location,
                mainIcon = painterResource(id = R.drawable.departament)
            )
            Spacer(Modifier.height(5.dp))
            DropLocationMnPo(
                selectOptionChange = { municipality = it },
                text = "Municipio",
                options = municipalityList,
                mainIcon = null
            )
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = { gene = it },
                text = gene.toString(),
                options = listOf("Hombre", "Mujer", "Prefiero no decir"),
                mainIcon = painterResource(id = com.parce.components_ui.R.drawable.genders)
            )
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = { ethnicGroup = it },
                text = ethnicGroup.toString(),
                options = listOf(
                    "Afrocolombiano",
                    "Sin Pertenencia a Grupo",
                    "Mestizo", "Indígena",
                    "Palenquero", "Raizal",
                    "Romo Gitano", "Sin información"
                ),
                mainIcon = painterResource(id = com.parce.components_ui.R.drawable.groups)
            )
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = { presentsDisability = it },
                text = presentsDisability.toString(),
                options = listOf("NO", "SI"),
                mainIcon = painterResource(id = com.parce.components_ui.R.drawable.ic_disability)
            )
            DataTimeString(dateState = { birthday = it }, value = birthday.toString())
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = {
                    onClickSave.invoke(
                        listOf(
                            companyName,
                            identificationType.toString(),
                            idNumber,
                            companyType,
                            kindCompany.toString(),
                            economicActivity,
                            phone,
                            contactPerson,
                            department.toString(),
                            municipality.toString(),
                            address,
                            birthday,
                            gene,
                            ethnicGroup,
                            presentsDisability
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
