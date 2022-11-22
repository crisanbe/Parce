@file:OptIn(ExperimentalPagerApi::class)

package com.gerotac.parce.ui.navegation.holder

import android.Manifest
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.gerotac.auth.codeverificationRegister.presentation.components.confirmationcode.VerificationView
import com.gerotac.auth.login.presentation.components.logincomposables.LoginScreen
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.newpasswordforget.presentation.components.newpassword.NewPasswordForgetView
import com.gerotac.auth.profileUser.presentation.ui.HomeCompany
import com.gerotac.auth.profileUser.presentation.ui.ProfileCompany
import com.gerotac.auth.register.presentation.ui.RegisterScreen
import com.gerotac.auth.assignrequirement.presentation.ui.assign.AssignToStudentScreen
import com.gerotac.auth.assignrequirement.presentation.ui.assign.AssignToTeacherScreen
import com.gerotac.auth.requirement.presentation.ui.homerequirement.detail.DetailScreen
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.ExitAlert
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.RequirementScreen
import com.gerotac.auth.requirement.presentation.ui.intervention.InterventionScreen
import com.gerotac.auth.sendemailforgotmypassword.presentation.components.resendnewcode.SendEmailForgotPasswordView
import com.gerotac.auth.updateuser.presentation.ui.updateUser.admin.AdminProfile
import com.gerotac.auth.updateuser.presentation.ui.updateUser.company.CompanyRegistrationPageView
import com.gerotac.auth.updateuser.presentation.ui.updateUser.company.CompanyRegistrationView
import com.gerotac.auth.updateuser.presentation.ui.updateUser.student.StudentProfile
import com.gerotac.auth.updateuser.presentation.ui.updateUser.teacher.TeacherProfile
import com.gerotac.auth.updateuser.presentation.ui.updateintohome.admin.UpdateAdminView
import com.gerotac.auth.updateuser.presentation.ui.updateintohome.company.UpdateCompanyView
import com.gerotac.auth.updateuser.presentation.ui.updateintohome.student.UpdateStudentView
import com.gerotac.auth.updateuser.presentation.ui.updateintohome.teacher.UpdateTeacherView
import com.gerotac.auth.validateCodeVerificationForgotPassword.presentation.components.codeforgotpassword.CodeForgotPasswordView
import com.gerotac.auth.verificationcodevalidateemail.presentation.components.VerificationCodeValidateEmailView
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.drawer.RequirementActions
import com.gerotac.parce.ui.animations.EnterAnimation
import com.gerotac.parce.ui.permission.PermissionScreen
import com.gerotac.parce.ui.providers.LocalNavHost
import com.gerotac.parce.ui.theme.ParceTheme
import com.gerotac.parce.ui.view.ImageSliderView
import com.gerotac.parce.ui.view.SplashScreenView
import com.gerotac.parce.ui.view.StartUpView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HolderScreen(onStatusBarColorChange: (color: Color) -> Unit) {
    val controller = LocalNavHost.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    ParceTheme {
        val navigationActions = remember(controller) {
            RequirementActions(controller)
        }

        Box {
            ScaffoldSection(
                controller = controller,
                scaffoldState = scaffoldState,
                onStatusBarColorChange = onStatusBarColorChange,
                onClickIconButton = { scope.launch { scaffoldState.drawerState.open() } },
                onClickDestination = {
                    controller.navigate(it) {
                        controller.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                },
                navigateToHome = navigationActions.navigateToHome,
                navigateToDetail = navigationActions.navigateToDetail
            )
        }
    }
}

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalPermissionsApi::class,
    ExperimentalCoilApi::class,
    ExperimentalPagerApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun ScaffoldSection(
    controller: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState,
    onStatusBarColorChange: (color: Color) -> Unit,
    navigateToHome: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    onClickIconButton: (ScaffoldState) -> Unit,
    onClickDestination: (screen: String) -> Unit
) {
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    )
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            scaffoldState.snackbarHostState
        },
    ) { paddingValues ->
        Column(
            Modifier.padding(paddingValues)
        ) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = controller,
                startDestination = AppScreens.SplashScreen.route
            ) {
                composable(route = AppScreens.SplashScreen.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    SplashScreenView()
                }
                composable(route = AppScreens.Slider.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    ImageSliderView()
                }
                composable(route = AppScreens.StartUp.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    StartUpView(controller)
                    BackHandler(true) {}
                }
                composable(route = AppScreens.PermissionScreen.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    PermissionScreen(permissionsStates = permissionsState, controller)
                }
                composable(
                    route = AppScreens.LoginScreen.route + "?email={email}",
                    arguments = listOf(navArgument(name = "email") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) {
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        LoginScreen(
                            controller,
                            valueEmail = it.arguments?.getString("email"),
                            rememberScaffoldState()
                        )
                    }
                }
                composable(route = AppScreens.RegisterScreen.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    RegisterScreen(controller, scaffoldState)
                }
                composable(
                    route = AppScreens.VerificationCodeValidateEmail.route + "?email={email}",
                    arguments = listOf(navArgument(name = "email") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    VerificationCodeValidateEmailView(
                        controller,
                        valueEmail = it.arguments?.getString("email"),
                    )
                }
                composable(
                    route = AppScreens.VerificationCode.route + "?email={email}",
                    arguments = listOf(navArgument(name = "email") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    VerificationView(
                        controller,
                        valueEmail = it.arguments?.getString("email"),
                    )
                }
                composable(
                    route = AppScreens.CodeForgotPassword.route + "?email={email}",
                    arguments = listOf(navArgument(name = "email") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) {

                    CodeForgotPasswordView(
                        controller,
                        valueEmail = it.arguments?.getString("email")
                    )
                    BackHandler(true) {}
                }
                composable(route = AppScreens.SendEmailForgotPassword.route) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    SendEmailForgotPasswordView(controller)
                }
                composable(
                    route = AppScreens.NewPasswordForget.route + "?token={token}",
                    arguments = listOf(navArgument(name = "token") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    NewPasswordForgetView(
                        controller,
                        valueToken = it.arguments?.getString("token")
                    )
                }
                composable(route = AppScreens.CompanyRegistration.route) {
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        CompanyRegistrationView(controller)
                        BackHandler(true) {}
                    }
                }
                composable(
                    route = AppScreens.CompanyProfileViewPagination.route +
                            "?companyName={companyName}&identificationType={identificationType}&" +
                            "idNumber={idNumber}&companyType={companyType}&kindCompany={kindCompany}&" +
                            "economicActivity={economicActivity}&phone={phone}",
                    arguments = listOf(
                        navArgument(name = "companyName") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "identificationType") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "idNumber") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "companyType") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "kindCompany") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "economicActivity") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "phone") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ) { backStackEntry ->
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        CompanyRegistrationPageView(
                            navController = controller,
                            companyName = backStackEntry.arguments?.getString("companyName"),
                            identificationType = backStackEntry.arguments?.getString("identificationType"),
                            idNumber = backStackEntry.arguments?.getString("idNumber"),
                            companyType = backStackEntry.arguments?.getString("companyType"),
                            kindCompany = backStackEntry.arguments?.getString("kindCompany"),
                            economicActivity = backStackEntry.arguments?.getString("economicActivity"),
                            phone = backStackEntry.arguments?.getString("phone")
                        )
                    }
                }
                composable(
                    route = AppScreens.UpdateCompanyView.route +
                            "?companyName={companyName}&identificationType={identificationType}&" +
                            "idNumber={idNumber}&companyType={companyType}&kindCompany={kindCompany}&" +
                            "economicActivity={economicActivity}&contactPerson={contactPerson}&" +
                            "department={department}&municipality={municipality}&" +
                            "academicProgram={academicProgram}&birthday={birthday}&" +
                            "gene={gene}&ethnicGroup={ethnicGroup}&presentsDisability={presentsDisability}&" +
                            "address={address}&phone={phone}",
                    arguments = listOf(
                        navArgument(name = "companyName") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "identificationType") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "idNumber") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "companyType") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "kindCompany") {
                            type = NavType.IntType
                            defaultValue = 0
                        },
                        navArgument(name = "economicActivity") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "contactPerson") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "department") {
                            type = NavType.IntType
                            defaultValue = 0
                        },
                        navArgument(name = "municipality") {
                            type = NavType.IntType
                            defaultValue = 0
                        },
                        navArgument(name = "academicProgram") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "birthday") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "gene") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "ethnicGroup") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "presentsDisability") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "address") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "phone") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ) { backStackEntry ->
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        UpdateCompanyView(
                            navController = controller,
                            companyName = backStackEntry.arguments?.getString("companyName"),
                            identificationType = backStackEntry.arguments?.getString("identificationType"),
                            idNumber = backStackEntry.arguments?.getString("idNumber"),
                            companyType = backStackEntry.arguments?.getString("companyType"),
                            kindCompany = backStackEntry.arguments?.getInt("kindCompany"),
                            economicActivity = backStackEntry.arguments?.getString("economicActivity"),
                            contactPerson = backStackEntry.arguments?.getString("contactPerson"),
                            department = backStackEntry.arguments?.getInt("department"),
                            municipality = backStackEntry.arguments?.getInt("municipality"),
                            academicProgram = backStackEntry.arguments?.getString("academicProgram"),
                            birthday = backStackEntry.arguments?.getString("birthday"),
                            gene = backStackEntry.arguments?.getString("gene"),
                            ethnicGroup = backStackEntry.arguments?.getString("ethnicGroup"),
                            presentsDisability = backStackEntry.arguments?.getString("presentsDisability"),
                            address = backStackEntry.arguments?.getString("address"),
                            phone = backStackEntry.arguments?.getString("phone"),
                            title = DrawerScreens.CompanyProfile,
                            scaffoldState = scaffoldState,
                            onClickIconButton = {
                                onClickIconButton(it)
                            },
                            onClickDestination = {
                                onClickDestination(it)
                            }
                        )
                    }
                }
                composable(
                    route = AppScreens.UpdateAdmin.route +
                            "?teacherName={teacherName}&identificationType={identificationType}&" +
                            "idNumber={idNumber}&birthday={birthday}&gene={gene}&" +
                            "ethnicGroup={ethnicGroup}&presentsDisability={presentsDisability}&" +
                            "&phone={phone}",
                    arguments = listOf(
                        navArgument(name = "teacherName") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "identificationType") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "idNumber") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "birthday") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "gene") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "ethnicGroup") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "presentsDisability") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "phone") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ) { backStackEntry ->
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        UpdateAdminView(
                            navController = controller,
                            adminName = backStackEntry.arguments?.getString("teacherName"),
                            identificationType = backStackEntry.arguments?.getString("identificationType"),
                            idNumber = backStackEntry.arguments?.getString("idNumber"),
                            birthday = backStackEntry.arguments?.getString("birthday"),
                            gene = backStackEntry.arguments?.getString("gene"),
                            ethnicGroup = backStackEntry.arguments?.getString("ethnicGroup"),
                            presentsDisability = backStackEntry.arguments?.getString("presentsDisability"),
                            phone = backStackEntry.arguments?.getString("phone"),
                            title = DrawerScreens.CompanyProfile,
                            scaffoldState = scaffoldState,
                            onClickIconButton = {
                                onClickIconButton(it)
                            },
                            onClickDestination = {
                                onClickDestination(it)
                            }
                        )
                    }
                }
                composable(
                    route = AppScreens.UpdateTeacherView.route +
                            "?teacherName={teacherName}&identificationType={identificationType}&" +
                            "idNumber={idNumber}&birthday={birthday}&gene={gene}&" +
                            "ethnicGroup={ethnicGroup}&presentsDisability={presentsDisability}&" +
                            "&phone={phone}",
                    arguments = listOf(
                        navArgument(name = "teacherName") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "identificationType") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "idNumber") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "birthday") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "gene") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "ethnicGroup") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "presentsDisability") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "phone") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ) { backStackEntry ->
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        UpdateTeacherView(
                            navController = controller,
                            teacherName = backStackEntry.arguments?.getString("teacherName"),
                            identificationType = backStackEntry.arguments?.getString("identificationType"),
                            idNumber = backStackEntry.arguments?.getString("idNumber"),
                            birthday = backStackEntry.arguments?.getString("birthday"),
                            gene = backStackEntry.arguments?.getString("gene"),
                            ethnicGroup = backStackEntry.arguments?.getString("ethnicGroup"),
                            presentsDisability = backStackEntry.arguments?.getString("presentsDisability"),
                            phone = backStackEntry.arguments?.getString("phone"),
                            title = DrawerScreens.CompanyProfile,
                            scaffoldState = scaffoldState,
                            onClickIconButton = {
                                onClickIconButton(it)
                            },
                            onClickDestination = {
                                onClickDestination(it)
                            }
                        )
                    }
                }
                composable(
                    route = AppScreens.UpdateStudentView.route +
                            "?studentName={studentName}&identificationType={identificationType}&" +
                            "idNumber={idNumber}&birthday={birthday}&gene={gene}&" +
                            "ethnicGroup={ethnicGroup}&presentsDisability={presentsDisability}&" +
                            "&academic_program={academic_program}&phone={phone}",
                    arguments = listOf(
                        navArgument(name = "studentName") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "identificationType") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "idNumber") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "birthday") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "gene") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "ethnicGroup") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "presentsDisability") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument(name = "academic_program") {
                            type = NavType.IntType
                            defaultValue = 0
                        },
                        navArgument(name = "phone") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )
                ) { backStackEntry ->
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        UpdateStudentView(
                            navController = controller,
                            studentName = backStackEntry.arguments?.getString("studentName"),
                            identificationType = backStackEntry.arguments?.getString("identificationType"),
                            idNumber = backStackEntry.arguments?.getString("idNumber"),
                            birthday = backStackEntry.arguments?.getString("birthday"),
                            gene = backStackEntry.arguments?.getString("gene"),
                            ethnicGroup = backStackEntry.arguments?.getString("ethnicGroup"),
                            presentsDisability = backStackEntry.arguments?.getString("presentsDisability"),
                            academic_program = backStackEntry.arguments?.getInt("academic_program"),
                            phone = backStackEntry.arguments?.getString("phone"),
                            title = DrawerScreens.CompanyProfile,
                            scaffoldState = scaffoldState,
                            onClickIconButton = {
                                onClickIconButton(it)
                            },
                            onClickDestination = {
                                onClickDestination(it)
                            }
                        )
                    }
                }
                composable(route = AppScreens.RequirementScreen.route) {
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        RequirementScreen(controller)
                    }
                }
                composable(
                    route = AppScreens.DetailScreen.route,
                    arguments = listOf(
                        navArgument("id") {
                            type = NavType.IntType
                            defaultValue = 0
                        }
                    )
                ) {
                    DetailScreen(
                        navController = controller,
                        upPress = navigateToHome
                    )
                }
                composable(
                    route = AppScreens.AssignToTeacherScreen.route +
                            "?codeTeacher={codeTeacher}",
                    arguments = listOf(
                        navArgument(name = "codeTeacher") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                    )
                ) { backStackEntry ->
                    AssignToTeacherScreen(
                        navController = controller,
                        codeTeacher = backStackEntry.arguments?.getString("codeTeacher").toString(),
                        upPress = navigateToHome,
                        scaffoldState = scaffoldState
                    )
                }
                composable(
                    route = AppScreens.AssignToStudentScreen.route +
                            "?code={code}",
                    arguments = listOf(
                        navArgument(name = "code") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                    )
                ) { backStackEntry ->
                    AssignToStudentScreen(
                        navController = controller,
                        code = backStackEntry.arguments?.getString("code").toString(),
                        upPress = navigateToHome,
                        scaffoldState = scaffoldState
                    )
                }
                composable(route = AppScreens.InterventionScreen.route) {
                    InterventionScreen(
                        navController = controller,
                        scaffoldState = scaffoldState,
                        onClickIconButton = { itScaffold ->
                            onClickIconButton(itScaffold)
                        },
                        onClickDestination = { itString ->
                            onClickDestination(itString)
                        },
                        onItemClicked = {}
                    )
                }
                composable(route = AppScreens.AdminProfile.route) {
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        AdminProfile(controller)
                        BackHandler(true) {}
                    }
                }
                composable(route = AppScreens.TeacherProfile.route) {
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        TeacherProfile(controller)
                        BackHandler(true) {}
                    }
                }
                composable(route = AppScreens.StudentProfile.route) {
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        StudentProfile(controller)
                        BackHandler(true) {}
                    }
                }
                composable(
                    route = DrawerScreens.CompanyHome.route + "?user={user}",
                    arguments = listOf(navArgument(name = "user") {
                        type = NavType.StringType
                        defaultValue = ""
                    })
                ) {
                    onStatusBarColorChange(MaterialTheme.colors.background)
                    HomeCompany(
                        title = DrawerScreens.CompanyHome,
                        navController = controller,
                        scaffoldState = scaffoldState,
                        nameUser = it.arguments?.getString("user"),
                        onClickIconButton = { itScaffold ->
                            onClickIconButton(itScaffold)
                        },
                        onClickDestination = { itString ->
                            onClickDestination(itString)
                        },
                        onItemClicked = {
                            navigateToDetail(it)
                        }
                    )
                }
                composable(
                    route = DrawerScreens.CompanyProfile.route,
                ) {
                    EnterAnimation {
                        ProfileCompany(
                            title = DrawerScreens.CompanyProfile,
                            navController = controller,
                            scaffoldState = scaffoldState,
                            onClickIconButton = {
                                onClickIconButton(it)
                            },
                            onClickDestination = {
                                onClickDestination(it)
                            }
                        )
                        BackHandler(true) {}
                    }
                }
                composable(
                    route = DrawerScreens.EXIT.route,
                ) {
                    EnterAnimation {
                        LaunchedEffect(key1 = true) {
                            userRepo?.getTokenLoginState()
                                ?.collect { tokenLogin ->
                                    withContext(Dispatchers.Main) {
                                        if (tokenLogin != "") {
                                            controller.navigate(AppScreens.ExitAlert.route)
                                        }
                                    }
                                }
                        }
                    }
                }
                composable(route = AppScreens.ExitAlert.route) {
                    EnterAnimation {
                        onStatusBarColorChange(MaterialTheme.colors.background)
                        ExitAlert(controller)
                    }
                }
            }
        }
    }
}
