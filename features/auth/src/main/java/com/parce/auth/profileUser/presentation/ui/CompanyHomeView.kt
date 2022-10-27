package com.parce.auth.profileUser.presentation.ui

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.auth.requirement.domain.model.getrequirement.Result
import com.parce.auth.requirement.presentation.ui.homerequirement.*
import com.parce.auth.requirement.presentation.ui.homerequirement.search.SearchBar
import com.parce.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.parce.auth.requirement.presentation.viewmodel.RequirementViewModel
import com.parce.components_ui.componets.*
import com.parce.components_ui.componets.alertdialog.ViewModelDialog
import com.parce.components_ui.componets.drawer.AppScreens
import com.parce.components_ui.componets.drawer.Drawer
import com.parce.components_ui.componets.drawer.DrawerScreens
import com.parce.components_ui.componets.progress.CircularProgress
import com.parce.components_ui.componets.progress.ProgressIndicator
import com.parce.core.util.UiEvent
import com.parce.shared.commons.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalAnimationApi
@Composable
fun HomeCompany(
    title: DrawerScreens,
    navController: NavController,
    scaffoldState: ScaffoldState,
    nameUser: String?,
    onItemClicked: (Int) -> Unit,
    onClickIconButton: (ScaffoldState) -> Unit,
    onClickDestination: (screen: String) -> Unit,
    viewModelGetRequirement: RequirementViewModel = hiltViewModel(),
    viewModel: DetailRequirementViewModel = hiltViewModel(),
) {
    val state = viewModelGetRequirement.stateGetRequirement
    val context = LocalContext.current
    val eventFlow = viewModelGetRequirement.uiEvent.receiveAsFlow()
    val scaffold = rememberScaffoldState()
    var visible by remember { mutableStateOf(false) }
    var value by remember { mutableStateOf("") }
    val lifecycleTokenScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    val viemodel: ViewModelDialog = hiltViewModel()
    val systemUiController = rememberSystemUiController()
    val query = viewModelGetRequirement.query.value
    SideEffect { systemUiController.setSystemBarsColor(color = com.parce.auth.theme.ColorLogin) }

    BackHandler(true) { viemodel.showDialog() }
    CircularProgress(showDialog = showDialog)
    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffold.snackbarHostState.showSnackbar(
                        message = event.message.asString(context = context)
                    )
                }
                else -> Unit
            }
        }
    }
    com.parce.components_ui.componets.alertdialog.DialogExit(
        text = "Deseas salir de la sesión ?",
        onClickYes = {
            showDialog = !showDialog
            lifecycleTokenScope.launch {
                userRepo?.getTokenLoginState()?.collect { token ->
                    withContext(Dispatchers.Main) {
                        token.let { userRepo?.deleteTokenLoginState() }
                        navController.navigate(AppScreens.LoginScreen.route)
                    }
                }
            }
        })

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(tween(300)),
        exit = slideOutHorizontally()
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    title = title.title,
                    buttonIcon = Icons.Outlined.Menu,
                    icon = Icons.Outlined.Home,
                    scaffoldState = scaffoldState,
                    onClickIconButton = { scaffoldState ->
                        onClickIconButton(scaffoldState)
                    }
                )
            },
            drawerContent = {
                Drawer(navController = navController,
                    onClickDestination = { screen ->
                        onClickDestination(screen)
                    }
                )
            },
            floatingActionButton = {
                FloatingButtonHome(
                    text = "Crear requerimiento",
                    onClickFloatingButton = {
                        navController.navigate(AppScreens.RequirementScreen.route)
                    })
            },
            isFloatingActionButtonDocked = true,
            bottomBar = {
                HomeBottomBar(
                    showPrevious = state.showPrevious,
                    showNext = state.showNext,
                    onPreviousPressed = { viewModelGetRequirement.doGetRequirement(false) },
                    onNextPressed = { viewModelGetRequirement.doGetRequirement(true) }
                )
            },
        ) { PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(PaddingValues)
                    .padding(horizontal = 20.dp)
                    .background(Color.Transparent),
            ) {
                SearchBar(
                    nameUser = nameUser.toString(),
                    query = query,
                    onSearch = {

                    }
                )
                RequirementsContent(
                    isLoading = state.isLoading,
                    resultRequirement = state.getRequirement,
                    onItemClicked = { onItemClicked(it) })
            }
        }
    }
    LaunchedEffect(visible) {
        visible = true
    }
}

@Composable
private fun RequirementsContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    resultRequirement: List<Result> = emptyList(),
    onItemClicked: (Int) -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 1.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                item {
                    Text(
                        text = "Requerimientos",
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(25.dp)
                            .padding(vertical = 1.dp)
                    )
                }
                items(resultRequirement.size) { index ->
                    HomeRequirements(
                        resultRequirement = resultRequirement[index],
                        onItemClicked = { onItemClicked(it) }
                    )
                }
            }
        )
        Column() {
            repeat(7) {
                if (isLoading) AnimationEffect()
            }
        }
    }
}

