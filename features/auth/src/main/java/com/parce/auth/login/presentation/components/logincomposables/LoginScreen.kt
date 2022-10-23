package com.parce.auth.login.presentation.components.logincomposables

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.parce.auth.R
import com.parce.auth.UserStore
import com.parce.auth.login.data.remote.logindto.LoginDto
import com.parce.auth.login.presentation.viewmodel.LoginViewModel
import com.parce.auth.protodata.ProtoUserRepo
import com.parce.auth.protodata.ProtoUserRepoImpl
import com.parce.auth.protodata.UserStoreSerializer
import com.parce.components_ui.componets.button.ButtonValidationLogin
import com.parce.components_ui.componets.TextButtonNavigation
import com.parce.components_ui.componets.TopPartLogin
import com.parce.components_ui.componets.progress.ProgressIndicator
import com.parce.components_ui.componets.drawer.AppScreens
import com.parce.components_ui.componets.drawer.DrawerScreens
import com.parce.core.util.UiEvent
import com.parce.shared.commons.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val Context.userDataStore: DataStore<UserStore> by dataStore(
    fileName = Constant.DATA_STORE_FILE_NAME,
    serializer = UserStoreSerializer
)
var userRepo: ProtoUserRepo? = null

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController,
    valueEmail: String?,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = Color.White,
                    contentColor = Color.Yellow,
                    snackbarData = data,
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(20),
                    backgroundColor = Color.Black
                )
            }
        },
        content = {
            Box(Modifier.fillMaxSize()) {
                HeaderLogin(navController, Modifier.align(Alignment.TopEnd))
                BodyLogin(Modifier.align(Alignment.Center),
                    navController = navController,
                    scaffoldState = scaffoldState,
                    valueEmail = valueEmail,
                    onClickForgotPassword =
                    { navController.navigate(AppScreens.SendEmailForgotPassword.route) },
                    onClickRegister = { navController.navigate(AppScreens.RegisterScreen.route) }
                )
            }
        })
}

@Composable
fun HeaderLogin(navController: NavController, modifier: Modifier) {
    TopPartLogin(modifier = modifier,
        onClickAction = { navController.navigate(AppScreens.StartUp.route) })
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BodyLogin(
    modifier: Modifier = Modifier,
    navController: NavController,
    scaffoldState: ScaffoldState,
    valueEmail: String?,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onClickForgotPassword: () -> Unit,
    onClickRegister: () -> Unit
) {
    val context = LocalContext.current
    userRepo = ProtoUserRepoImpl(LocalContext.current.userDataStore)
    val hideKeyboard = LocalSoftwareKeyboardController.current
    var nameError by remember { mutableStateOf(false) }
    val eventFlow = loginViewModel.uiEvent.receiveAsFlow()
    val emailValue: String = if (valueEmail.toString() == "null") "" else valueEmail.toString()
    val email: String by loginViewModel.email.observeAsState(initial = emailValue)
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)
    val state = loginViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = com.parce.auth.theme.ColorLogin,
        )
    }
    BackHandler(true) { navController.navigate(AppScreens.StartUp.route) }
    ProgressIndicator(
        modifier = modifier.offset(x = 150.dp, y = 90.dp),
        isDisplayed = state.value.isLoading
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.size(150.dp))
        IncomeComponent(modifier = modifier)
        Spacer(modifier = Modifier.size(16.dp))
        Email(nameError, email) {
            loginViewModel.onLoginChanged(
                Login = LoginDto(
                    email = it,
                    password = password
                )
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Password(nameError, password) {
            loginViewModel.onLoginChanged(
                Login = LoginDto(
                    email = email,
                    password = it
                )
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        TextButtonNavigation(
            text = stringResource(id = R.string.TextButton_Forgot_Password),
            onClick = { onClickForgotPassword.invoke() }
        )
        Spacer(modifier = Modifier.size(10.dp))
        ButtonValidationLogin(
            isLoginEnable = isLoginEnable,
            onClickAction = {
                hideKeyboard?.hide()
                nameError = email.isBlank()
                nameError = password.isBlank()
                scope.launch {
                    loginViewModel.doLogin(
                        LoginDto(email, password)
                    )
                    eventFlow.collect { event ->
                        when (event) {
                            is UiEvent.Success -> {
                                val token = state.value.token?.authorization?.token
                                val rolCompany = state.value.token?.user?.role
                                val nameUser = state.value.token?.user?.name
                                userRepo?.saveRolLogin(rolCompany)
                                userRepo?.saveTokenLoginState(token)
                                userRepo?.saveNameUser(nameUser)
                                val emailVerified =
                                    state.value.token?.user?.email_verified
                                val emailUser = state.value.token?.user?.email
                                val statusUser = state.value.token?.user?.user_status
                                withContext(Dispatchers.Main) {
                                    if (emailVerified == null) {
                                        navController
                                            .navigate(
                                                AppScreens
                                                    .VerificationCodeValidateEmail.route
                                                        + "?email=$emailUser"
                                            )
                                    } else if (rolCompany == "empresa" &&
                                        statusUser == "pending"
                                    ) {
                                        navController
                                            .navigate(
                                                AppScreens.CompanyRegistration.route
                                            )
                                    } else if (rolCompany == "docente" &&
                                        statusUser == "pending"
                                    ) {
                                        navController
                                            .navigate(
                                                AppScreens.TeacherProfile.route
                                            )
                                    } else if (rolCompany == "estudiante" &&
                                        statusUser == "pending"
                                    ) {
                                        navController
                                            .navigate(
                                                AppScreens.StudentProfile.route
                                            )
                                    } else {
                                        navController.navigate(
                                            DrawerScreens.CompanyHome.route
                                                    + "?user=$nameUser"
                                        )
                                    }
                                }
                            }
                            is UiEvent.ShowSnackBar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message.asString(context)
                                )
                            }
                            else -> Unit
                        }
                    }
                }
            },
            text = stringResource(R.string.Button_Enter)
        )
        Spacer(modifier = Modifier.size(10.dp))
        FooterLogin(modifier = Modifier) { onClickRegister.invoke() }
        Spacer(modifier = Modifier.size(80.dp))
    }
}

@Composable
fun FooterLogin(modifier: Modifier, onClickRegister: () -> Unit) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.Text_Not_registered))
        TextButtonNavigation(
            text = stringResource(id = R.string.TextButton_Register),
            onClick = { onClickRegister.invoke() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen(
        navController = rememberNavController(),
        "",
        rememberScaffoldState()
    )
}
