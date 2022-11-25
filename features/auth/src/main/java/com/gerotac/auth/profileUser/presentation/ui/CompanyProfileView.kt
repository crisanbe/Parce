package com.gerotac.auth.profileUser.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gerotac.auth.profileUser.domain.model.User
import com.gerotac.auth.profileUser.presentation.viewmodel.GetProfileViewModel
import com.gerotac.auth.requirement.di.HeaderRequirement
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.AnimationEffect
import com.gerotac.components_ui.componets.FloatingButtonHome
import com.gerotac.components_ui.componets.TopBar
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

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


