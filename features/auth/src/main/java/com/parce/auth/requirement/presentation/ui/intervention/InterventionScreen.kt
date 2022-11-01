package com.parce.auth.requirement.presentation.ui.intervention

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parce.auth.requirement.domain.model.getrequirement.Result
import com.parce.auth.requirement.presentation.ui.homerequirement.AnimationEffect
import com.parce.auth.requirement.presentation.ui.homerequirement.HomeRequirements
import com.parce.auth.requirement.presentation.viewmodel.RequirementViewModel
import com.parce.components_ui.componets.TopBar
import com.parce.components_ui.componets.drawer.Drawer

@Composable
fun InterventionScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    onItemClicked: (Int) -> Unit,
    onClickIconButton: (ScaffoldState) -> Unit,
    onClickDestination: (screen: String) -> Unit,
    viewModelGetRequirement: RequirementViewModel = hiltViewModel()
) {
    var visible by remember { mutableStateOf(false) }
    val state = viewModelGetRequirement.stateGetRequirement.collectAsState()
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(tween(300)),
        exit = slideOutHorizontally()
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    title = "",
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
        ) { PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(PaddingValues)
                    .padding(horizontal = 20.dp)
                    .background(Color.Transparent),
            ) {
                RequirementsContent(
                    onItemClicked = { onItemClicked(it) },
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
    hint: String = "",
    isLoading: Boolean = false,
    resultRequirement: List<Result> = ArrayList(),
    viewModelGetRequirement: RequirementViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
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
                        text = "Intervenciones",
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