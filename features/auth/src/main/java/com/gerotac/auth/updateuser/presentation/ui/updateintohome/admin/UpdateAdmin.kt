package com.gerotac.auth.updateuser.presentation.ui.updateintohome.admin

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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gerotac.auth.R
import com.gerotac.auth.login.presentation.components.logincomposables.userDataStore
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.profileUser.presentation.ui.Drawer
import com.gerotac.auth.protodata.ProtoUserRepoImpl
import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.gerotac.auth.updateuser.presentation.viewmodel.UpdateUserViewModel
import com.gerotac.components_ui.componets.progress.LinearProgressBar
import com.gerotac.components_ui.componets.DividerIcon
import com.gerotac.components_ui.componets.TopBar
import com.gerotac.components_ui.componets.datatime.DataTimeString
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.dropdown.DropString
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun UpdateAdminView(
    viewModel: UpdateUserViewModel = hiltViewModel(),
    navController: NavController,
    title: DrawerScreens,
    scaffoldState: ScaffoldState,
    onClickIconButton: (ScaffoldState) -> Unit,
    onClickDestination: (screen: String) -> Unit,
    adminName: String?,
    identificationType: String?,
    idNumber: String?,
    birthday: String?,
    gene: String?,
    phone: String?,
    ethnicGroup: String?,
    presentsDisability: String?
) {
    val context = LocalContext.current
    val eventFlow = viewModel.uiEvent.receiveAsFlow()
    val lifecycleTokenScope = rememberCoroutineScope()
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
                UpdateTeacher(
                    teacherName = adminName,
                    identificationType = identificationType,
                    idNumber = idNumber,
                    birthday = birthday,
                    gene = gene,
                    phone = phone,
                    ethnicGroup = ethnicGroup,
                    presentsDisability = presentsDisability,
                    navController = navController,
                    onClickSave = {
                        lifecycleTokenScope.launch {
                            viewModel.doUpdateUser(
                                ParameterUpdateUserDto(
                                    name = it[0],
                                    type_document = it[1],
                                    document = it[2],
                                    phone = it[3],
                                    birthday = it[4],
                                    gener = it[5],
                                    group_etnic = it[6],
                                    presents_disability = it[7]
                                )
                            )
                            eventFlow.collect { event ->
                                when (event) {
                                    is UiEvent.Success -> {
                                        navController.navigate(
                                            DrawerScreens.CompanyHome.route
                                                    + "?user=parce😁"
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
    LaunchedEffect(visible) {
        visible = true
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UpdateTeacher(
    navController: NavController,
    onClickSave: (List<String>) -> Unit,
    teacherName: String?,
    identificationType: String?,
    idNumber: String?,
    birthday: String?,
    gene: String?,
    phone: String?,
    ethnicGroup: String?,
    presentsDisability: String?
) {
    val hideKeyboard = LocalSoftwareKeyboardController.current
    var teacherName by remember { mutableStateOf(teacherName) }
    var identificationType by remember { mutableStateOf(identificationType) }
    var idNumber by remember { mutableStateOf(idNumber) }
    var phone by remember { mutableStateOf(phone) }
    var birthday by remember { mutableStateOf(birthday) }
    var gene by remember { mutableStateOf(gene) }
    var ethnicGroup by remember { mutableStateOf(ethnicGroup) }
    var presentsDisability by remember { mutableStateOf(presentsDisability) }
    userRepo = ProtoUserRepoImpl(LocalContext.current.userDataStore)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(R.string.Text_Admin_profile),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 55.dp, top = 1.dp)
        )
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(12.dp))
            TextField(
                value = teacherName.toString(),
                onValueChange = { teacherName = it },
                label = { Text(stringResource(id = R.string.TextField_full_name)) },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = stringResource(id = R.string.TextField_full_name),
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
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = { identificationType = it },
                text = identificationType.toString(),
                options = listOf("NIT", "Cedula", "Pasaporte", "Cédula de Extranjería"),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.identity)
            )
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = { gene = it },
                text = gene.toString(),
                options = listOf("Hombre", "Mujer", "Prefiero no decir"),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.genders)
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
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.groups)
            )
            Spacer(Modifier.height(5.dp))
            DropString(
                ValueState = { presentsDisability = it },
                text = presentsDisability.toString(),
                options = listOf("NO", "SI"),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.ic_disability)
            )
            Spacer(Modifier.height(5.dp))
            DataTimeString(dateState = { birthday = it }, value = birthday.toString())
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = {
                    onClickSave.invoke(
                        listOf(
                            teacherName,
                            identificationType.toString(),
                            idNumber,
                            phone,
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
