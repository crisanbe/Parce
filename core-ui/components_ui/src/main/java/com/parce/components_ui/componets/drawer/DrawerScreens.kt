package com.parce.components_ui.componets.drawer

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens(route = "SplashScreen")
    object Slider : AppScreens(route = "Slider")
    object StartUp : AppScreens(route = "StartUp")
    object LoginScreen : AppScreens(route = "LoginScreen")
    object SendEmailForgotPassword : AppScreens(route = "SendEmailForgotPassword")
    object CodeForgotPassword : AppScreens(route = "CodeForgotPassword")
    object VerificationCodeValidateEmail : AppScreens(route = "VerificationCodeValidateEmail")
    object NewPasswordForget : AppScreens(route = "NewPasswordForget")
    object RegisterScreen : AppScreens(route = "RegisterScreen")
    object VerificationCode : AppScreens(route = "VerificationCode")
    object CompanyRegistration : AppScreens(route = "CompanyRegistration")
    object CompanyProfileViewPagination : AppScreens(route = "CompanyProfileViewPagination")
    object TeacherProfile : AppScreens(route = "TeacherProfile")
    object StudentProfile : AppScreens(route = "StudentProfile")
    object UpdateCompanyView : AppScreens(route = "UpdateCompanyView")
    object UpdateTeacherView : AppScreens(route = "UpdateTeacherView")
    object UpdateStudentView : AppScreens(route = "UpdateStudentView")
    object RequirementScreen : AppScreens(route = "RequirementScreen")
    object PermissionScreen : AppScreens(route = "PermissionScreen")
    object DetailScreen : AppScreens(route = "DetailScreen?id={id}") {
        fun passId(id: Int): String {
            return "DetailScreen?id=$id"
        }
    }

    object ExitAlert : AppScreens(route = "ExitAlert")
}

class RequirementActions(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(DrawerScreens.CompanyHome.route) {
            popUpTo(navController.graph.findStartDestination().arguments.size) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToDetail = { id: Int ->
        navController.navigate(AppScreens.DetailScreen.passId(id)) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
        }
    }
}

sealed class DrawerScreens(
    val route: String,
    val title: String,
    val IconDrawer: ImageVector
) {
    object CompanyHome :
        DrawerScreens(route = "CompanyHome", title = "Home", IconDrawer = Icons.Outlined.Home)

    object CompanyProfile : DrawerScreens(
        route = "CompanyProfile",
        title = "Profile",
        IconDrawer = Icons.Outlined.Person
    )

    object EXIT :
        DrawerScreens(route = "Exit", title = "Salir", IconDrawer = Icons.Outlined.ExitToApp)
}
