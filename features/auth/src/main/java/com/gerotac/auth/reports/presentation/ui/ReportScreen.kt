package com.gerotac.auth.reports.presentation.ui

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.LocalActivity
import androidx.compose.material.icons.rounded.MergeType
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gerotac.auth.login.presentation.viewmodel.LoginViewModel
import com.gerotac.auth.profileUser.presentation.ui.Drawer
import com.gerotac.components_ui.componets.TopBar
import com.gerotac.components_ui.componets.button.ButtonValidation
import com.gerotac.components_ui.componets.button.ButtonWithIcon
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.progress.ProgressIndicator
import com.gerotac.components_ui.componets.ui.theme.ParceTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.receiveAsFlow

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReportScreen(
    title: DrawerScreens,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onClickIconButton: (ScaffoldState) -> Unit,
    onClickDestination: (screen: String) -> Unit,
) {
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
                    BodyReport(
                        Modifier.align(Alignment.Center),
                        navController = navController,
                        scaffoldState = scaffoldState
                    )
                }
            })
    }
    LaunchedEffect(visible) {
        visible = true
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BodyReport(
    modifier: Modifier = Modifier,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    val context = LocalContext.current
    val hideKeyboard = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = com.gerotac.auth.theme.ColorLogin,
        )
    }
    BackHandler(true) { navController.navigate(AppScreens.StartUp.route) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ButtonWithIcon(
            onClick = { navController.navigate(AppScreens.CompanyReportScreen.route) },
            modifier = modifier,
            textButton = "Reporte por empresa",
            icon = Icons.Outlined.Apartment
        )
        ButtonWithIcon(
            onClick = {},
            modifier = modifier,
            textButton = "Reporte por genero",
            icon = Icons.Outlined.Transgender
        )
        ButtonWithIcon(
            onClick = {},
            modifier = modifier,
            textButton = "Reporte por ciudad",
            icon = Icons.Outlined.LocationCity
        )
        ButtonWithIcon(
            onClick = {},
            modifier = modifier,
            textButton = "Reporte por grupo étnico",
            icon = Icons.Outlined.Groups
        )
        ButtonWithIcon(
            onClick = {},
            modifier = modifier,
            textButton = "Reporte por discapacidad",
            icon = Icons.Outlined.Blind
        )
        ButtonWithIcon(
            onClick = {},
            modifier = modifier,
            textButton = "Reporte por departamento",
            icon = Icons.Outlined.Map
        )
        ButtonWithIcon(
            onClick = {},
            modifier = modifier,
            textButton = "Reporte por tipo de sociedad",
            icon = Icons.Outlined.Handshake
        )
        ButtonWithIcon(
            onClick = {},
            modifier = modifier,
            textButton = "Reporte por area de intervención",
            icon = Icons.Rounded.LocalActivity
        )
        ButtonWithIcon(
            onClick = {},
            modifier = modifier,
            textButton = "Reporte por tipo de intervención",
            icon = Icons.Rounded.MergeType
        )
        Spacer(modifier = Modifier.height(10.dp))
        /*ButtonValidation(text = "Consultar reporte") {

        }*/
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ReportPreview() {
    ParceTheme() {
        ReportScreen(
            navController = rememberNavController(),
            scaffoldState = rememberScaffoldState(),
            onClickIconButton = { it },
            onClickDestination = { it },
            title = DrawerScreens.Report
        )
    }
}
