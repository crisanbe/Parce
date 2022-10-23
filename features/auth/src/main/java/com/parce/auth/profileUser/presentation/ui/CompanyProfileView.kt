package com.parce.auth.profileUser.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.model.content.Repeater
import com.parce.auth.R
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.auth.profileUser.domain.model.User
import com.parce.auth.profileUser.presentation.viewmodel.GetProfileViewModel
import com.parce.auth.requirement.presentation.ui.homerequirement.AnimationEffect
import com.parce.components_ui.componets.FloatingButtonHome
import com.parce.components_ui.componets.TopBar
import com.parce.components_ui.componets.drawer.AppScreens
import com.parce.components_ui.componets.drawer.Drawer
import com.parce.components_ui.componets.progress.ProgressIndicator
import com.parce.components_ui.componets.drawer.DrawerScreens
import com.parce.core.util.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalAnimationApi
@Composable
fun ProfileCompany(
    title: DrawerScreens,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onClickIconButton: (ScaffoldState) -> Unit,
    onClickDestination: (screen: String) -> Unit
) {
    ProfileCompanyView(
        title = title,
        navController = navController,
        scaffoldState = scaffoldState,
        onClickIconButton = { scaffold ->
            onClickIconButton(scaffold)
        },
        onClickDestination = { screen ->
            onClickDestination(screen)
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun ProfileCompanyView(
    title: DrawerScreens,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onClickIconButton: (ScaffoldState) -> Unit,
    onClickDestination: (screen: String) -> Unit,
    viewModelProfile: GetProfileViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModelProfile.state.collectAsState()
    var visible by remember { mutableStateOf(false) }
    val eventFlow = viewModelProfile.uiEvent.receiveAsFlow()
    val scaffold = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffold.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(tween(300)),
        exit = slideOutHorizontally()
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    title = title.title,
                    buttonIcon = Icons.Outlined.Menu,
                    icon = Icons.Outlined.BorderColor,
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
            floatingActionButton = {
                FloatingButtonHome(
                    onClickFloatingButton = {
                        navController.navigate(AppScreens.RequirementScreen.route)
                    })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp)
                    .fillMaxSize()
            ) {
                state.value.profileUserResponse?.let {
                    ProfileContent(
                        modifier = Modifier.padding(innerPadding),
                        isLoading = state.value.isLoading,
                        user = it,
                        navController = navController
                    )
                }
            }
        }
    }
    LaunchedEffect(visible) {
        visible = true
    }
}

@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    user: List<User> = emptyList(),
    navController: NavController
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.White
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 4.dp),
            content = {
                items(user.size) { index ->
                    CardProfile(
                        modifier = Modifier.fillMaxSize(),
                        item = user[index],
                        navController = navController
                    )
                }
            }
        )
        Column() {
            repeat(5) {
                if (isLoading) AnimationEffect()
            }
        }
    }
}


