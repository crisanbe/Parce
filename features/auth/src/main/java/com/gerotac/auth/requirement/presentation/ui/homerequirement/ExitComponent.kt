package com.gerotac.auth.requirement.presentation.ui.homerequirement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.components_ui.componets.alertdialog.ExitAlertDialog
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ExitAlert(
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://cdn.icon-icons.com/icons2/2596/PNG/512/bye_icon_155703.png")
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 100.dp)
                .clip(CircleShape)
        )
        ExitAlertDialog(
            text = "Deseas salir de la sesion👍",
            onClickYes = {
                showDialog = !showDialog
                scope.launch {
                    userRepo?.getTokenLoginState()
                        ?.collect { tokenLogin ->
                            withContext(Dispatchers.Main) {
                                if (tokenLogin != "") {
                                    tokenLogin.let { userRepo?.deleteTokenLoginState() }
                                    navController.navigate(AppScreens.StartUp.route)
                                }
                            }
                        }
                }
            },
            onClickNot = {
                navController.navigate(DrawerScreens.CompanyHome.route)
            }
        )
    }

}