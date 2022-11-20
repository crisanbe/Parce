@file:Suppress("EqualsBetweenInconvertibleTypes")

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
import com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea.ResultStudent
import com.gerotac.auth.dropdownapi.dropdown.presentation.ui.DropStudentByArea
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
fun AssignToStudentScreen(
    navController: NavController,
    code: String,
    scaffoldState: ScaffoldState,
    upPress: () -> Unit,
    getDropStudentByAcademicViewModel: GetApisDropViewModel = hiltViewModel(),
    assignViewModel: AssignRequirementViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val hideKeyboard = LocalSoftwareKeyboardController.current
    val stateGetStudent = getDropStudentByAcademicViewModel.stateStudentByArea.collectAsState()
    val eventFlow = assignViewModel.uiEvent.receiveAsFlow()
    LaunchedEffect(true) {
        scope.launch {
            getDropStudentByAcademicViewModel.doGetStudentByArea(
                query = code.toInt()
            )
        }
    }
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
                HeaderAssignToStudent(navController, upPress)
                stateGetStudent.value.studentByAreaState.let { listStudentByArea ->
                    BodyAssignToStudent(Modifier.align(Alignment.Center),
                        navController = navController,
                        code = code,
                        listStudent = listStudentByArea,
                        onclickAssignStudent = {
                            hideKeyboard?.hide()
                            scope.launch {
                                assignViewModel.assignRequirementStudent(
                                    request = AssignRequest(
                                        user = it,
                                        idRequirement = code.toInt()
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
                        }
                    )
                }
            }
        })
}

@Composable
fun HeaderAssignToStudent(navController: NavController, upPress: () -> Unit) {
    TopPart(onClickAction = { upPress() })
}

@Composable
fun BodyAssignToStudent(
    modifier: Modifier = Modifier,
    navController: NavController,
    listStudent: List<ResultStudent> = emptyList(),
    code: String,
    onclickAssignStudent: (List<Int>) -> Unit,
    assignTeacherViewModel: AssignRequirementViewModel = hiltViewModel()
) {
    var teacher: List<Int> by remember { mutableStateOf(listOf()) }
    val state = assignTeacherViewModel.state.collectAsState()
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
            modifier = Modifier.padding(30.dp),
            text = stringResource(R.string.TextField_Assign_Requirement_Student) + " #️⃣${code}",
            fontSize = 22.sp,
            color = Color.Black,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        DropStudentByArea(
            selectOptionChange = { teacher = arrayListOf(it) },
            text = "Estudiante",
            options = listStudent,
            mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.docente_asign)
        )
        Spacer(modifier = Modifier.size(16.dp))
        ButtonValidation(
            onClick = { onclickAssignStudent.invoke(teacher) },
            text = "Asignar"
        )
        Spacer(modifier = Modifier.height(70.dp))
    }
    LinearProgressBar(state.value.isLoading, "Asignando...")
}

@Composable
fun FooterAssignToStudent(modifier: Modifier, onClickRegister: () -> Unit) {
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
fun AssignToStudentPreview() {
    AssignToStudentScreen(
        navController = rememberNavController(),
        "",
        rememberScaffoldState(),
        upPress = {}
    )
}
