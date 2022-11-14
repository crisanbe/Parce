package com.gerotac.parce.ui.permission

import android.Manifest
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.google.accompanist.permissions.*
import com.gerotac.components_ui.componets.alertdialog.ExitAlertDialog
import com.gerotac.components_ui.componets.alertdialog.ViewModelDialog
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.parce.ui.view.StartUpView

@ExperimentalPermissionsApi
@Composable
fun PermissionScreen(
    permissionsStates: MultiplePermissionsState,
    navController: NavController
) {
    val viewModelDialog: ViewModelDialog = hiltViewModel()
    var showDialog by remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current
    BackHandler(true) { viewModelDialog.showDialog() }
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_START) {
                    permissionsStates.launchMultiplePermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
    permissionsStates.permissions.forEach { perm ->
        when (perm.permission) {
            Manifest.permission.READ_EXTERNAL_STORAGE -> {
                when {
                    perm.status.isGranted -> {
                        StartUpView(navController = navController)
                    }
                    perm.status.shouldShowRationale -> {
                        ExitAlertDialog(
                            text = "se necesita permiso para acceder a la Galería",
                            onClickYes = {
                                showDialog = !showDialog
                                navController.navigate(AppScreens.StartUp.route)
                            },
                            {}
                        )
                    }
                    perm.isPermanentlyDenied() -> {
                        ExitAlertDialog(
                            text = "El permiso de la galería fue permanentemente denegado." +
                                    " Puedes habilitarlo en la aplicación" +
                                    " ajustes.",
                            onClickYes = {
                                showDialog = !showDialog
                                navController.navigate(AppScreens.RequirementScreen.route)
                            },
                            {}
                        )
                    }
                }
            }
        }
    }

}