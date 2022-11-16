@file:Suppress("UnusedEquals")

package com.gerotac.auth.profileUser.presentation.ui

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.requirement.di.HeaderRequirement
import com.gerotac.auth.requirement.domain.model.getrequirement.Result
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.AnimationEffect
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.HomeBottomBar
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.HomeRequirements
import com.gerotac.auth.requirement.presentation.viewmodel.RequirementViewModel
import com.gerotac.components_ui.R
import com.gerotac.components_ui.componets.TopBar
import com.gerotac.components_ui.componets.alertdialog.ViewModelDialog
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.progress.CircularProgress
import com.gerotac.core.util.UiEvent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
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
    viewModelGetRequirement: RequirementViewModel = hiltViewModel()
) {
    val state = viewModelGetRequirement.stateGetRequirement.collectAsState()
    val context = LocalContext.current
    val eventFlow = viewModelGetRequirement.uiEvent.receiveAsFlow()
    val scaffold = rememberScaffoldState()
    var visible by remember { mutableStateOf(false) }
    val lifecycleTokenScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    val viemodel: ViewModelDialog = hiltViewModel()
    val systemUiController = rememberSystemUiController()
    val query = viewModelGetRequirement.query.value
    SideEffect { systemUiController.setSystemBarsColor(color = com.gerotac.auth.theme.ColorLogin) }

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
    com.gerotac.components_ui.componets.alertdialog.DialogExit(
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
                when (HeaderRequirement.getRol()["rol"]) {
                    "empresa" -> {
                        FloatingButton(
                            text = "Crear requerimiento",
                            onClickFloatingButton = {
                                navController.navigate(AppScreens.RequirementScreen.route)
                            })
                    }
                    else -> {}
                }

            },
            floatingActionButtonPosition = FabPosition.Center,
            bottomBar = {
                HomeBottomBar(
                    showPrevious = state.value.showPrevious,
                    showNext = state.value.showNext,
                    onPreviousPressed = { viewModelGetRequirement.doGetRequirement(null, false) },
                    onNextPressed = { viewModelGetRequirement.doGetRequirement(null, true) }
                )
            },
        ) { PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(PaddingValues)
                    .padding(horizontal = 20.dp)
                    .background(Color.Transparent),
            ) {
                RequirementsContent(
                    onItemClicked = { onItemClicked(it) },
                    nameUser = nameUser.toString(),
                    query = query,
                    isLoading = state.value.isLoading,
                    resultRequirement = state.value.getRequirement
                )
            }
        }
    }
    LaunchedEffect(visible) {
        visible = true
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun RequirementsContent(
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    nameUser: String,
    query: String,
    hint: String = "",
    isLoading: Boolean = false,
    resultRequirement: List<Result> = ArrayList(),
    viewModelGetRequirement: RequirementViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Surface(color = MaterialTheme.colors.background) {
        Column(horizontalAlignment = Alignment.Start) {
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = "Hola, ${HeaderRequirement.getNameUser()["name"]}",
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = colorResource(id = R.color.primary)
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.size(10.dp))
            Box(modifier = modifier) {
                IconButton(onClick = { }) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { viewModelGetRequirement.onQueryChanged(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModelGetRequirement.doGetRequirement(query)
                                keyboardController?.hide()
                            },
                        ),
                        singleLine = true,
                        label = { Text("Requerimiento") },
                        placeholder = { Text("Código!") },
                        maxLines = 1,
                        shape = RoundedCornerShape(30.dp),
                        trailingIcon = {
                            if (query.isNotBlank()) {
                                IconButton(onClick = { query.equals("")}) {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Limpiar campo"
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .width(400.dp)
                            .offset(y = (-13).dp)
                            .onFocusChanged {
                                isHintDisplayed = (!it.isFocused) && query.isNotEmpty()
                            }
                    )
                }
                if (isHintDisplayed) {
                    Text(
                        text = hint,
                        color = Color.LightGray,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                    )
                }
            }
        }
    }
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
                itemsIndexed(
                    items = resultRequirement
                ) { _, resultRequirements ->
                    HomeRequirements(
                        resultRequirement = resultRequirements,
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

