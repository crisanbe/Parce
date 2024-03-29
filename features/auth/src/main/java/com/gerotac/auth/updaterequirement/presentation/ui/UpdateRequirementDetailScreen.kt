package com.gerotac.auth.updaterequirement.presentation.ui

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.gerotac.auth.R
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.ResultArea
import com.gerotac.auth.dropdownapi.dropdown.presentation.ui.DropAreas
import com.gerotac.auth.dropdownapi.dropdown.presentation.viewmodel.GetApisDropViewModel
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.mToast
import com.gerotac.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.gerotac.auth.updaterequirement.data.remote.dto.request.RequestUpdateRequirement
import com.gerotac.auth.updaterequirement.presentation.viewmodel.UpdateRequirementViewModel
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.button.ButtonWithShadow
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.progress.LinearProgressBar
import com.gerotac.components_ui.componets.textfield.FieldValue
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun UpdateRequirementDetailScreen(
    code: String,
    navController: NavController,
    upPress: () -> Unit,
    viewModel: DetailRequirementViewModel = hiltViewModel()
) {
    BackHandler(true) { navController.navigate(DrawerScreens.CompanyHome.route) }
    UpdateRequirementContent(
        upPress = upPress,
        code = code,
        navController = navController
    )
}

@Composable
private fun UpdateRequirementContent(
    code: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModelLocation: GetApisDropViewModel = hiltViewModel(),
    upPress: () -> Unit
) {
    val stateGetAreas = viewModelLocation.stateArea.collectAsState()
    Box(modifier.fillMaxSize()) {
        Column {
            HeaderUpdateRequirement(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                code = code,
                upPress = upPress
            )
            stateGetAreas.value.areaState.let { listArea ->
                BodyUpdateRequirement(
                    navController = navController,
                    code = code,
                    listAreaIntervention = listArea
                )
            }
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun HeaderUpdateRequirement(
    modifier: Modifier = Modifier,
    code: String,
    upPress: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(modifier = modifier, scaffoldState = scaffoldState, snackbarHost = {
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
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TopPart(onClickAction = { upPress() })
            Text(
                text = stringResource(R.string.TextField_Update_Requirement_Number) + " #️⃣${code}",
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun BodyUpdateRequirement(
    code: String,
    listAreaIntervention: List<ResultArea>,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: UpdateRequirementViewModel = hiltViewModel()
) {
    val hideKeyboard = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val activity = LocalContext.current as Activity
    val state = viewModel.state.collectAsState()
    val eventFlow = viewModel.uiEvent.receiveAsFlow()
    var areaAcademic by remember { mutableStateOf(1) }
    var description by remember { mutableStateOf("") }
    var impactProblem by remember { mutableStateOf("") }
    var efectProblem by remember { mutableStateOf("") }
    var causeProblem by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        DropAreas(
            selectOptionChange = { areaAcademic = it },
            text = stringResource(R.string.TextField_Area_intervention),
            options = listAreaIntervention,
            mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.identity)
        )
        OutlinedTextField(
            modifier = Modifier
                .width(350.dp)
                .height(150.dp),
            value = description,
            onValueChange = { description = it },
            label = { Text(stringResource(id = R.string.TextField_Description_problem)) },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            maxLines = 5,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onDone = { hideKeyboard?.hide() }),
            leadingIcon = {
                Icon(
                    painter = rememberAsyncImagePainter(
                        model = com.gerotac.components_ui.R.drawable.description
                    ),
                    tint = Color.Black,
                    contentDescription = "",
                )
                Divider(
                    modifier = Modifier
                        .width(34.3.dp)
                        .height(30.dp)
                        .padding(start = 33.dp)
                )
            })
        FieldValue(
            ValueState = { impactProblem = it },
            text = impactProblem,
            valueText = "Impacto del problema",
            icon = rememberAsyncImagePainter(
                model = com.gerotac.components_ui.R.drawable.impact
            )
        )
        FieldValue(
            ValueState = { efectProblem = it },
            text = efectProblem,
            valueText = "Efecto del problema",
            icon = rememberAsyncImagePainter(
                model = com.gerotac.components_ui.R.drawable.effect
            )
        )
        FieldValue(
            ValueState = { causeProblem = it },
            text = causeProblem,
            valueText = "Causa del problema",
            icon = rememberAsyncImagePainter(
                model = com.gerotac.components_ui.R.drawable.cause
            )
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            ButtonWithShadow(
                modifier = Modifier
                    .width(350.dp)
                    .height(60.dp),
                color = Color.Black,
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    scope.launch {
                        viewModel.doUpdateRequirement(
                            id = code.toInt(),
                            RequestUpdateRequirement(
                                area_intervention = areaAcademic,
                                description = description,
                                cause_problem = causeProblem,
                                efect_problem = efectProblem,
                                impact_problem = impactProblem,
                            )
                        )
                        eventFlow.collectLatest { event ->
                            when (event) {
                                is UiEvent.Success -> {
                                    mToast(context = activity, "Se actualizo correctamente🗃️")
                                    navController.navigate(DrawerScreens.CompanyHome.route)
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Se actualizo correctamente🏅",
                                        actionLabel = "Continue"
                                    )
                                }
                                is UiEvent.ShowSnackBar -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = event.message.asString(activity)
                                    )
                                }
                                else -> Unit
                            }
                        }
                    }
                },
                textoBoton = "Guardar",
            )
        }
    }
    LinearProgressBar(isDisplayed = state.value.isLoading)
}