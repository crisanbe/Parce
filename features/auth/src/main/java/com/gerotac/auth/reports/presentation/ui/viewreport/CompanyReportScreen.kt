package com.gerotac.auth.reports.presentation.ui.viewreport

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gerotac.auth.profileUser.presentation.ui.Drawer
import com.gerotac.components_ui.componets.TopBar
import com.gerotac.components_ui.componets.button.ButtonValidation
import com.gerotac.components_ui.componets.button.ButtonWithIcon
import com.gerotac.components_ui.componets.datatime.DataTime
import com.gerotac.components_ui.componets.datatime.DataTimeString
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.ui.theme.ParceTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CompanyReportScreen(
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
    var dateInitial by remember { mutableStateOf("") }
    var dateFinish by remember { mutableStateOf("") }
    val hideKeyboard = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    BackHandler(true) { navController.navigate(AppScreens.StartUp.route) }
    Column(
        modifier = modifier.padding(all = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DataTimeString(dateState = { dateInitial = it }, value = dateInitial, "Fecha inicial")
        Spacer(modifier = Modifier.height(10.dp))
        DataTimeString(dateState = { dateFinish = it }, value = dateFinish, "Fecha final")
        Spacer(modifier = Modifier.height(10.dp))
        ButtonValidation(text = "Consultar reporte") {

        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}
