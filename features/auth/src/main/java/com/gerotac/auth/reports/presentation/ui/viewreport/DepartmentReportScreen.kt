package com.gerotac.auth.reports.presentation.ui.viewreport

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Report
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.gerotac.auth.profileUser.presentation.ui.Drawer
import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.presentation.statereports.FileDownloadScreenState
import com.gerotac.auth.reports.presentation.viewmodel.ReportResponseViewModel
import com.gerotac.auth.reports.presentation.viewmodel.ReportFileViewModel
import com.gerotac.components_ui.componets.TopBar
import com.gerotac.components_ui.componets.button.ButtonValidation
import com.gerotac.components_ui.componets.button.ButtonWithShadow
import com.gerotac.components_ui.componets.datatime.DataTimeString
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.progress.CircularProgress
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DepartmentReportScreen(
    title: DrawerScreens,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onClickIconButton: (ScaffoldState) -> Unit,
    onClickDestination: (screen: String) -> Unit,
) {
    val viewModel: ReportFileViewModel = viewModel()
    var visible by remember { mutableStateOf(false) }
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(tween(300)),
        exit = slideOutHorizontally()
    ) {
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
            topBar = {
                TopBar(
                    title = title.title,
                    buttonIcon = Icons.Outlined.Menu,
                    icon = Icons.Outlined.Report,
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
            content = {
                Box(Modifier.fillMaxSize()) {
                    BodyDepartmentReport(
                        Modifier.align(Alignment.Center),
                        navController = navController,
                        scaffoldState = scaffoldState,
                        state = viewModel.state,
                        onBackToIdleRequested = viewModel::onIdleRequested,
                    )
                }
            })
    }
    LaunchedEffect(visible) {
        visible = true
    }
}

@Composable
fun BodyDepartmentReport(
    modifier: Modifier = Modifier,
    navController: NavController,
    scaffoldState: ScaffoldState,
    state: FileDownloadScreenState,
    onBackToIdleRequested: () -> Unit,
    viewModelReportFile: ReportFileViewModel = hiltViewModel(),
    viewModelReportResponse: ReportResponseViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val stateReportResponse = viewModelReportResponse.state.collectAsState()
    val eventFlow = viewModelReportResponse.uiEvent.receiveAsFlow()
    var dateInitial by remember { mutableStateOf("") }
    var dateFinish by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    BackHandler(true) { navController.navigate(DrawerScreens.Report.route) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Reporte por departamento.",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            style = MaterialTheme.typography.h6,
        )
        Spacer(modifier = Modifier.height(10.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://img.freepik.com/vector-premium/informe-auditoria-investigacion-datos-impuestos-financieros-ventas-vector-dibujos-animados-plana_212005-120.jpg")
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = null,
            modifier = Modifier.clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(10.dp))
        DataTimeString(dateState = { dateInitial = it }, value = dateInitial, "Fecha inicial", icon = painterResource(id = com.gerotac.components_ui.R.drawable.calendar_month))
        Spacer(modifier = Modifier.height(5.dp))
        DataTimeString(dateState = { dateFinish = it }, value = dateFinish, "Fecha final", icon = painterResource(id = com.gerotac.components_ui.R.drawable.date_range))
        Spacer(modifier = Modifier.height(10.dp))
        when (state) {
            FileDownloadScreenState.Idle -> {
                Text(
                    text = "Descargar el archivo🗂️.",
                    style = MaterialTheme.typography.h6,
                )
                Spacer(modifier = Modifier.height(8.dp))
                CircularProgress(showDialog = stateReportResponse.value.isLoading)
                ButtonValidation(
                    text = "Consultar reporte.",
                    onClick = {
                        scope.launch() {
                            viewModelReportFile.downloadFileDepartment(ReportRequest(dateInitial, dateFinish))
                            viewModelReportResponse.doReport(
                                ReportRequest(
                                    dateInitial,
                                    dateFinish
                                )
                            )
                            eventFlow.collect { event ->
                                when (event) {
                                    is UiEvent.Success -> {}
                                    is UiEvent.ShowSnackBar -> {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = event.message.asString(activity)
                                        )
                                    }
                                    else -> Unit
                                }
                            }
                        }
                    })
            }
            is FileDownloadScreenState.Downloading -> {
                DownloadFilesWithProgressLayout(progress = state.progress)
            }
            is FileDownloadScreenState.Failed -> {
                Text(
                    text = "Fallo la descarga❌",
                    style = MaterialTheme.typography.h6,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onBackToIdleRequested) {
                    Text(
                        text = "OK",
                        style = MaterialTheme.typography.button,
                    )
                }
            }
            FileDownloadScreenState.Downloaded -> {
                Text(
                    text = "Descarga completa✔️" +
                            "\nVerifica en descargas.",
                    color = Color.Green,
                    style = MaterialTheme.typography.h6,
                )
                Spacer(modifier = Modifier.height(8.dp))
                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
                    }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonWithShadow(
                        modifier = Modifier
                            .width(150.dp)
                            .height(65.dp),
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp),
                        onClick = { launcher.launch("application/pdf") },
                        textoBoton = "Ir a descargas🗃️"

                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    ButtonWithShadow(
                        modifier = Modifier
                            .width(150.dp)
                            .height(65.dp),
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp),
                        onClick = { onBackToIdleRequested() },
                        textoBoton = "Nueva consulta",
                    )
                }
            }
        }
    }
}

@Composable
private fun DownloadFilesWithProgressLayout(progress: Int) {
    Text(
        text = "Downloaded $progress%",
        style = MaterialTheme.typography.h6,
    )
    Spacer(modifier = Modifier.height(8.dp))
    LinearProgressIndicator(
        progress = progress.toFloat() / 100f,
        modifier = Modifier.fillMaxWidth(),
    )
}
