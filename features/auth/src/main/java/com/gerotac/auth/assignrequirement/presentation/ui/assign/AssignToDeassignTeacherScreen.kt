package com.gerotac.auth.assignrequirement.presentation.ui.assign

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gerotac.auth.R
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.request.AssignRequest
import com.gerotac.auth.assignrequirement.presentation.viewmodel.AssignRequirementViewModel
import com.gerotac.auth.dropdownapi.dropdown.domain.model.listateacher.ResultTeacher
import com.gerotac.auth.dropdownapi.dropdown.presentation.ui.DropDeassignRequirement
import com.gerotac.auth.dropdownapi.dropdown.presentation.viewmodel.GetApisDropViewModel
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.mToast
import com.gerotac.components_ui.componets.TextButtonNavigation
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.button.ButtonValidation
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.progress.LinearProgressBar
import com.gerotac.core.util.UiEvent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AssignToTeacherScreen(
    navController: NavController,
    codeTeacher: String,
    scaffoldState: ScaffoldState,
    upPress: () -> Unit,
    viewModelLocation: GetApisDropViewModel = hiltViewModel(),
    viewModel: AssignRequirementViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val hideKeyboard = LocalSoftwareKeyboardController.current
    val stateGetTeacher = viewModelLocation.stateTeacher.collectAsState()
    val eventFlow = viewModel.uiEvent.receiveAsFlow()
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
        },
        content = {
            Box(Modifier.fillMaxSize()) {
                HeaderTeacherScreen(navController, upPress)
                stateGetTeacher.value.teacherState.let { listTeacher ->
                    BodyAssign(Modifier.align(Alignment.Center),
                        navController = navController,
                        codeTeacher = codeTeacher,
                        listTeacher = listTeacher,
                        onclickAssignTeacher = {
                            hideKeyboard?.hide()
                            scope.launch {
                                viewModel.assignRequirementTeacher(
                                    AssignRequest(
                                        user = it,
                                        idRequirement = codeTeacher.toInt()
                                    )
                                )
                                eventFlow.collect { event ->
                                    when (event) {
                                        is UiEvent.Success -> {
                                            mToast(context, "Se asigno requerimiento👍")
                                            navController.navigate(DrawerScreens.CompanyHome.route)
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
                        },
                        onclickDeassignTeacher = {
                            hideKeyboard?.hide()
                            scope.launch {
                                viewModel.deassignRequirementStudent(
                                    AssignRequest(
                                        user = it,
                                        idRequirement = codeTeacher.toInt()
                                    )
                                )
                                eventFlow.collect { event ->
                                    when (event) {
                                        is UiEvent.Success -> {
                                            mToast(context, "Se desasigno requerimiento👍")
                                            navController.navigate(DrawerScreens.CompanyHome.route)
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
        })
}

@Composable
fun HeaderTeacherScreen(navController: NavController, upPress: () -> Unit) {
    TopPart(onClickAction = { upPress() })
}

@Composable
fun BodyAssign(
    modifier: Modifier = Modifier,
    navController: NavController,
    listTeacher: List<ResultTeacher> = emptyList(),
    codeTeacher: String,
    onclickAssignTeacher: (List<Int>) -> Unit,
    onclickDeassignTeacher: (List<Int>) -> Unit,
    viewModel: AssignRequirementViewModel = hiltViewModel()
) {
    var teacher: List<Int> by remember { mutableStateOf(listOf()) }
    val state = viewModel.state.collectAsState()
    val stateDeassign = viewModel.stateDeassign.collectAsState()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = com.gerotac.auth.theme.ColorLogin,
        )
    }
    BackHandler(true) { navController.navigate(DrawerScreens.CompanyHome.route) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = stringResource(R.string.TextField_Assign_Requirement) + " #️⃣${codeTeacher}",
            fontSize = 22.sp,
            color = Color.Black,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        DropDeassignRequirement(
            selectOptionChange = { teacher = arrayListOf(it) },
            text = "Docente",
            options = listTeacher,
            mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.docente_asign)
        )
        ButtonValidation(
            onClick = { onclickAssignTeacher.invoke(teacher) },
            text = "Asignar"
        )
        Spacer(modifier = Modifier.height(10.dp))
        ButtonValidation(
            onClick = { onclickDeassignTeacher.invoke(teacher) },
            text = "Desasignar"
        )
        Spacer(modifier = Modifier.height(70.dp))
    }
    LinearProgressBar(state.value.isLoading, "Asignando...")
    LinearProgressBar(stateDeassign.value.isLoading, "Desasignando...")
}

@Composable
fun FooterTeacherScreen(modifier: Modifier, onClickRegister: () -> Unit) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.Text_Not_registered))
        TextButtonNavigation(
            text = stringResource(id = R.string.TextButton_Register),
            onClick = { onClickRegister.invoke() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AssignTeacherScreenPreview() {
    AssignToTeacherScreen(
        navController = rememberNavController(),
        "",
        rememberScaffoldState(),
        upPress = {}
    )
}