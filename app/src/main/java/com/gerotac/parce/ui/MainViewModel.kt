package com.gerotac.parce.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
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
                            userRepo?.getUserStatus()?.collect { statusUser ->
                                withContext(Dispatchers.Main) {
                                    userRepo?.getRolLogin()?.collect { rol ->
                                        withContext(Dispatchers.Main) {
                                            val stateTokenAuth = MutableStateFlow(tokenAuthentication)
                                            val userState = MutableStateFlow(statusUser)
                                            val rolUser = MutableStateFlow(rol)
                                            if (stateTokenAuth.value == "" && prefsKey) {
                                                navController.navigate(AppScreens.StartUp.route)
                                                //navController.navigate(AppScreens.PermissionScreen.route)
                                            } else if (stateTokenAuth.value != "" && userState.value == "completed" && rolUser.value == "admin") {
                                                navController.navigate(DrawerScreens.CompanyHome.route + "?user=$nameUser")
                                            }else if (stateTokenAuth.value != "" && userState.value == "completed" && rolUser.value == "empresa") {
                                                navController.navigate(DrawerScreens.CompanyHome.route + "?user=$nameUser")
                                            }else if (stateTokenAuth.value != "" && userState.value == "completed" && rolUser.value == "docente") {
                                                navController.navigate(DrawerScreens.CompanyHome.route + "?user=$nameUser")
                                            }else if (stateTokenAuth.value != "" && userState.value == "completed" && rolUser.value == "estudiante") {
                                                navController.navigate(DrawerScreens.CompanyHome.route + "?user=$nameUser")
                                            }
                                            else if (stateTokenAuth.value != "" && userState.value == "pending" && rolUser.value == "admin")
                                            {
                                                navController.navigate(AppScreens.AdminProfile.route)
                                            }else if (stateTokenAuth.value != "" && userState.value == "pending" && rolUser.value == "empresa")
                                            {
                                                navController.navigate(AppScreens.CompanyRegistration.route)
                                            }else if (stateTokenAuth.value != "" && userState.value == "pending" && rolUser.value == "docente")
                                            {
                                                navController.navigate(AppScreens.TeacherProfile.route)
                                            }else if (stateTokenAuth.value != "" && userState.value == "pending" && rolUser.value == "estudiante")
                                            {
                                                navController.navigate(AppScreens.StudentProfile.route)
                                            }
                                            else {
                                                navController.navigate(AppScreens.Slider.route)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}