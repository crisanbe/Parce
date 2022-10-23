package com.parce.parce.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.components_ui.componets.drawer.AppScreens
import com.parce.components_ui.componets.drawer.DrawerScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    fun main(navController: NavController, prefsKey: Boolean) {
        viewModelScope.launch {
            delay(2000)
            userRepo?.getTokenLoginState()?.collect { tokenAuthentication ->
                withContext(Dispatchers.Main) {
                    userRepo?.getNameUser()?.collect { nameUser ->
                        withContext(Dispatchers.Main) {
                            val stateTokenAuth = MutableStateFlow(tokenAuthentication)
                            if (stateTokenAuth.value == "" && prefsKey) {
                                navController.navigate(AppScreens.StartUp.route)
                                navController.navigate(AppScreens.PermissionScreen.route)
                            } else if (stateTokenAuth.value != "") {
                                navController.navigate(
                                    DrawerScreens.CompanyHome.route + "?user=$nameUser"
                                )
                            } else {
                                navController.navigate(AppScreens.Slider.route)
                            }
                        }
                    }
                }
            }
        }
    }
}