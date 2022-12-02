@file:Suppress("UselessCallOnNotNull")

package com.gerotac.auth.updateuser.presentation.ui.updateUser.student

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gerotac.auth.R
import com.gerotac.auth.dropdownapi.dropdown.domain.model.responseacademic.ResultAcademic
import com.gerotac.auth.dropdownapi.dropdown.presentation.ui.DropAcademic
import com.gerotac.auth.dropdownapi.dropdown.presentation.viewmodel.GetApisDropViewModel
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserRequest
import com.gerotac.auth.updateuser.presentation.state.DocumentNumberState
import com.gerotac.auth.updateuser.presentation.state.PhoneNumberState
import com.gerotac.auth.updateuser.presentation.viewmodel.UpdateUserViewModel
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.alertdialog.DialogExit
import com.gerotac.components_ui.componets.alertdialog.ViewModelDialog
import com.gerotac.components_ui.componets.datatime.DataTimeAlternative
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.dropdown.DropDownAlternative
import com.gerotac.components_ui.componets.progress.ProgressIndicator
import com.gerotac.components_ui.componets.state.TextFieldValueState
import com.gerotac.components_ui.componets.state.ValueState
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StudentProfile(
    navController: NavController,
    viewModelUpdateUser: UpdateUserViewModel = hiltViewModel(),
    viewModelAcademic: GetApisDropViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val viewModelDialog: ViewModelDialog = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val stateAcademic = viewModelAcademic.stateAcademic.collectAsState()
    val eventFlow = viewModelUpdateUser.uiEvent.receiveAsFlow()
    val state = viewModelUpdateUser.state.collectAsState()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    BackHandler(true) { viewModelDialog.showDialog() }
    DialogExit(text = "Deseas salir sin actualizar tus datos!🤦‍♂", onClickYes = {
        showDialog = !showDialog
        scope.launch {
            userRepo?.getTokenRegister()?.collect { tokenRegister ->
                withContext(Dispatchers.Main) {
                    userRepo?.getTokenLoginState()?.collect { tokenLogin ->
                        withContext(Dispatchers.Main) {
                            if (tokenRegister != "" || tokenLogin != "") {
                                tokenLogin.let { userRepo?.deleteTokenLoginState() }
                                tokenRegister.let { userRepo?.deleteTokenRegister() }
                                navController.navigate(AppScreens.StartUp.route)
                            }
                        }
                    }
                }
            }
        }
    })
    Scaffold(modifier = Modifier, scaffoldState = scaffoldState, snackbarHost = {
        SnackbarHost(it) { data ->
            Snackbar(
                actionColor = Color.White,
                contentColor = Color.Yellow,
                snackbarData = data,
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(20),
                backgroundColor = Color.Black
            )
        }
    }, content = {
        Column(
            Modifier.fillMaxSize()
        ) {
            stateAcademic.value.academicProgramsState.let { listProgramAcademic ->
                StudentProfileView(
                    result = listProgramAcademic,
                    onClickSave = {
                        scope.launch {
                            viewModelUpdateUser.doUpdateUser(
                                ParameterUpdateUserRequest(
                                    name = it[0],
                                    type_document = it[1],
                                    document = it[2],
                                    gener = it[3],
                                    group_etnic = it[4],
                                    birthday = it[5],
                                    phone = it[6],
                                    presents_disability = it[7],
                                    academic_program = it[8].toInt()
                                )
                            )
                            eventFlow.collect { event ->
                                when (event) {
                                    is UiEvent.Success -> {
                                        navController.navigate(
                                            DrawerScreens.CompanyHome.route + "?user=Bienvenido!"
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
                    })
            }
            ProgressIndicator(
                modifier = Modifier.offset(x = 150.dp, y = (-790).dp),
                isDisplayed = state.value.isLoading
            )
        }
    })
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StudentProfileView(
    onClickSave: (List<String>) -> Unit,
    result: List<ResultAcademic> = emptyList(),
) {
    val focusManager = LocalFocusManager.current
    val hideKeyboard = LocalSoftwareKeyboardController.current
    val fullName: TextFieldValueState = remember { ValueState() }
    val viewModelDialog: ViewModelDialog = hiltViewModel()
    val identificationType: TextFieldValueState = remember { ValueState() }
    val idNumber: TextFieldValueState = remember { DocumentNumberState() }
    val gender: TextFieldValueState = remember { ValueState() }
    val ethnicGroup: TextFieldValueState = remember { ValueState() }
    val birthday: TextFieldValueState = remember { ValueState() }
    val phone: TextFieldValueState = remember { PhoneNumberState() }
    val hasDisability: TextFieldValueState = remember { ValueState() }
    var academicProgram by remember { mutableStateOf(0) }
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopPart(onClickAction = { viewModelDialog.showDialog() })
        Text(
            text = stringResource(R.string.Text_Student_profile),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 55.dp, top = 20.dp)
        )
        Column(
            Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.Text_We_need_some_data),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 45.dp)
                    .padding(top = 12.dp)
            )
            Spacer(Modifier.height(12.dp))
            FullNameStudent(fullName)
            DocumentStudent(idNumber)
            PhoneStudentProfile(phone)
            Spacer(Modifier.height(5.dp))
            DropDownAlternative(
                ValueState = identificationType,
                text = "Tipo de identificación",
                options = listOf("NIT", "Cedula", "Pasaporte", "Cédula de Extranjería"),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.identity)
            )
            Spacer(Modifier.height(5.dp))
            DropDownAlternative(
                ValueState = gender,
                text = "Tipo de genero",
                options = listOf("Hombre", "Mujer", "Prefiero no decir"),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.genders)
            )
            Spacer(Modifier.height(5.dp))
            DropDownAlternative(
                ValueState = ethnicGroup,
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
            DropDownAlternative(
                ValueState = hasDisability,
                text = "Tipo de discapasidad",
                options = listOf("NO", "SI"),
                keyboardActions = KeyboardActions(onDone = { hideKeyboard?.hide() }),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.ic_disability)
            )
            Spacer(Modifier.height(5.dp))
            DropAcademic(
                selectOptionChange = { academicProgram = it },
                text = "Programa académico",
                options = result,
                mainIcon = null
            )
            DataTimeAlternative(birthday)
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = {
                    hideKeyboard?.hide()
                    onClickSave.invoke(
                        listOf(
                            if(!fullName.text.isNullOrEmpty()){fullName.text}else{""},
                            if(!identificationType.text.isNullOrEmpty()){identificationType.text}else{""},
                            if(!idNumber.text.isNullOrEmpty()){idNumber.text}else{""},
                            if(!gender.text.isNullOrEmpty()){gender.text}else{""},
                            if(!ethnicGroup.text.isNullOrEmpty()){ethnicGroup.text}else{""},
                            if(!birthday.text.isNullOrEmpty()){birthday.text}else{""},
                            if(!phone.text.isNullOrEmpty()){phone.text}else{""},
                            if(!hasDisability.text.isNullOrEmpty()){hasDisability.text}else{""},
                            if(!academicProgram.toString().isNullOrEmpty()){academicProgram.toString()}else{""},
                        )
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

@Preview(showBackground = true)
@Composable
fun StudentProfilePreview() {
    StudentProfile(
        rememberNavController()
    )
}
