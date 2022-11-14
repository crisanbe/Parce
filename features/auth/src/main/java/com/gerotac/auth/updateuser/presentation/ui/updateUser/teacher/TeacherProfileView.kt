package com.gerotac.auth.updateuser.presentation.ui.updateUser.teacher

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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.gerotac.auth.R
import com.gerotac.auth.dropdownapi.dropdown.domain.model.dropmodel.Result
import com.gerotac.auth.dropdownapi.dropdown.presentation.viewmodel.GetApisDropViewModel
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.register.presentation.ui.TextFieldError
import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.gerotac.auth.updateuser.presentation.state.DocumentNumberState
import com.gerotac.auth.updateuser.presentation.state.PhoneNumberState
import com.gerotac.auth.updateuser.presentation.ui.updateUser.student.DocumentStudent
import com.gerotac.auth.updateuser.presentation.ui.updateUser.student.FullNameStudent
import com.gerotac.auth.updateuser.presentation.ui.updateUser.student.PhoneStudentProfile
import com.gerotac.auth.updateuser.presentation.viewmodel.UpdateUserViewModel
import com.gerotac.components_ui.componets.alertdialog.DialogExit
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.alertdialog.ViewModelDialog
import com.gerotac.components_ui.componets.datatime.DataTimeAlternative
import com.gerotac.components_ui.componets.progress.ProgressIndicator
import com.gerotac.components_ui.componets.state.TextFieldValueState
import com.gerotac.components_ui.componets.state.ValueState
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.dropdown.DropDownAlternative
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TeacherProfile(
    navController: NavController,
    viewModelUpdateUser: UpdateUserViewModel = hiltViewModel(),
    viewModelAcademic: GetApisDropViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val viewModelDialog: ViewModelDialog = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val eventFlow = viewModelUpdateUser.uiEvent.receiveAsFlow()
    val state = viewModelUpdateUser.state.collectAsState()
    val stateAcademic = viewModelAcademic.state.collectAsState()
    BackHandler(true) { viewModelDialog.showDialog() }
    DialogExit(
        text = "Deseas salir sin actualizar tus datos!🤦‍♂",
        onClickYes = {
            showDialog = !showDialog
            scope.launch {
                userRepo?.getTokenRegister()
                    ?.collect { tokenRegister ->
                        withContext(Dispatchers.Main) {
                            userRepo?.getTokenLoginState()
                                ?.collect { tokenLogin ->
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
        }
    )
    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
        snackbarHost = {
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
                stateAcademic.value.academicProgramsState.let {
                EducationalProfileView(
                    result = it,
                    onClickSave = {
                        scope.launch {
                            viewModelUpdateUser.doUpdateUser(
                                ParameterUpdateUserDto(
                                    name = it[0],
                                    type_document = it[1],
                                    document = it[2],
                                    gener = it[3],
                                    group_etnic = it[4],
                                    birthday = it[5],
                                    phone = it[6],
                                    presents_disability = it[7],
                                    academic_program = null,
                                    activity_economy = null,
                                    type_bussiness = null,
                                    type_society = null,
                                    person_contact = null,
                                    departament = null,
                                    municipality = null,
                                    address = null
                                )
                            )
                            eventFlow.collect { event ->
                                when (event) {
                                    is UiEvent.Success -> {
                                        navController.navigate(
                                            DrawerScreens.CompanyHome.route
                                                    + "?user=Bienvenido!"
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
                ProgressIndicator(
                    modifier = Modifier.offset(x = 150.dp, y = (-790).dp),
                    isDisplayed = state.value.isLoading
                )
            }
        })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EducationalProfileView(
    onClickSave: (List<String>) -> Unit,
    result: List<Result> = emptyList(),
    ) {
    val hideKeyboard = LocalSoftwareKeyboardController.current
    val viewModelDialog: ViewModelDialog = hiltViewModel()
    val fullName: TextFieldValueState = remember { ValueState() }
    val identificationType: TextFieldValueState = remember { ValueState() }
    val idNumber: TextFieldValueState = remember { DocumentNumberState() }
    val gender: TextFieldValueState = remember { ValueState() }
    val ethnicGroup: TextFieldValueState = remember { ValueState() }
    val birthday: TextFieldValueState = remember { ValueState() }
    val phoneNumberState: TextFieldValueState = remember { PhoneNumberState() }
    val hasDisability: TextFieldValueState = remember { ValueState() }
    var academicProgram by remember { mutableStateOf(0) }
    Column(
        Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopPart(onClickAction = { viewModelDialog.showDialog() })
        Text(
            text = stringResource(R.string.Text_Teacher_profile),
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
            idNumber.getErros()?.let { error -> TextFieldError(textError = error) }
            PhoneStudentProfile(phoneNumberState)
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
            /*DropAcademic(
                selectOptionChange = { academicProgram = it },
                text = "Programa académico",
                options = result,
                mainIcon = painterResource(id = R.drawable.view_module)
            )*/
            DataTimeAlternative(birthday)
            Spacer(Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .size(height = 55.dp, width = 300.dp)
                    .onFocusChanged { focusState ->
                        idNumber.onFocusedChange(focusState.isFocused)
                        phoneNumberState.onFocusedChange(focusState.isFocused)
                        if (!focusState.isFocused) {
                            phoneNumberState.enableShowErrors()
                            idNumber.enableShowErrors()
                        }
                    },
                onClick = {
                    hideKeyboard?.hide()
                    onClickSave.invoke(
                        listOf(
                            fullName.text,
                            identificationType.text,
                            idNumber.text,
                            gender.text,
                            ethnicGroup.text,
                            birthday.text,
                            phoneNumberState.text,
                            hasDisability.text
                        )
                    )
                },
                shape = RoundedCornerShape(percent = 45),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF030303),
                    contentColor = Color.White
                )
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
fun EducationalProfilePreview() {
    EducationalProfileView(
        onClickSave = {}
    )
}
