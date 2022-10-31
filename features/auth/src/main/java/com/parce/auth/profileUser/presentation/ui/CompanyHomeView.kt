package com.parce.auth.profileUser.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.auth.requirement.domain.model.getrequirement.Result
import com.parce.auth.requirement.presentation.ui.homerequirement.*
import com.parce.auth.requirement.presentation.ui.homerequirement.search.SearchBar
import com.parce.auth.requirement.presentation.ui.homerequirement.search.SearchEvent
import com.parce.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.parce.auth.requirement.presentation.viewmodel.RequirementViewModel
import com.parce.components_ui.R
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

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
                text = "Hola, $nameUser",
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
                BasicTextField(
                    value = query,
                    onValueChange = { viewModelGetRequirement.onQueryChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            viewModelGetRequirement.doGetRequirement(query)
                            keyboardController?.hide()
                        },
                    ),
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, CircleShape)
                        .background(Color.White, CircleShape)
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                        .onFocusChanged {
                            isHintDisplayed = (!it.isFocused) && query.isNotEmpty()
                        }
                )
                IconButton(onClick = { viewModelGetRequirement.doGetRequirement(query) }) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .offset(x = 302.dp, y = (-2).dp)
                            .size(44.dp)
                            .clip(RoundedCornerShape(22.dp))
                            .background(Color(0xFF21120B))
                            .scale(scale = 0.6f),
                        tint = Color.White
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

