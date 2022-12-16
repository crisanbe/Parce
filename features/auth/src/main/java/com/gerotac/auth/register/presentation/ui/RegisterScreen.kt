@file:Suppress("NAME_SHADOWING")

package com.gerotac.auth.register.presentation.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gerotac.auth.R
import com.gerotac.auth.login.presentation.components.logincomposables.userDataStore
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.protodata.ProtoUserRepoImpl
import com.gerotac.auth.register.data.remote.dto.RegisterDto
import com.gerotac.auth.register.presentation.state.EmailState
import com.gerotac.auth.register.presentation.state.PasswordState
import com.gerotac.auth.register.presentation.viewmodel.RegisterViewModel
import com.gerotac.components_ui.componets.*
import com.gerotac.components_ui.componets.button.ButtonValidation
import com.gerotac.components_ui.componets.dropdown.DropDown
import com.gerotac.components_ui.componets.progress.ProgressIndicator
import com.gerotac.components_ui.componets.state.TextFieldValueState
import com.gerotac.components_ui.componets.state.ValueState
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController, scaffoldState: ScaffoldState) {
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
        }, content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState(),
                        reverseScrolling = true
                    )
            ) {
                HeaderRegister(navController)
                BodyRegister(navController = navController, scaffoldState = scaffoldState)
            }
        })
}

@Composable
fun HeaderRegister(navController: NavController, modifier: Modifier = Modifier) {
    TopPart { navController.navigate(AppScreens.StartUp.route) }
}

@Composable
fun BodyRegister(
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsState()
    val eventFlow = viewModel.uiEvent.receiveAsFlow()
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    SideEffect { systemUiController.setSystemBarsColor(color = com.gerotac.auth.theme.ColorLogin) }
    Register(
        modifier,
        scaffoldState = scaffoldState,
        onClickAcceptTerms = { },
        onClickRegister = {
            scope.launch {
                viewModel.doRegister(
                    RegisterDto(
                        email = it[0],
                        name = it[1],
                        password = it[2],
                        password_confirmation = it[3],
                        role = it[4]
                    )
                )
                eventFlow.collect { event ->
                    val token = state.value.token?.authorization?.token
                    val email = state.value.token?.user?.email
                    when (event) {
                        is UiEvent.Success -> {
                            token?.let { it1 -> userRepo?.saveTokenRegister(it1) }
                            email?.let { it2 -> userRepo?.saveEmailUser(it2) }
                            userRepo?.getTokenRegister()
                                ?.collect { tokenRegister ->
                                    withContext(Dispatchers.Main) {
                                        if (tokenRegister != "")
                                            navController.navigate(
                                                route =
                                                AppScreens.VerificationCode.route + "?email=$email"
                                            )
                                    }
                                }
                            Toast.makeText(
                                navController.context, "Success", Toast.LENGTH_SHORT
                            ).show()
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
        onClickEnter = { navController.navigate(AppScreens.LoginScreen.route) })
    ProgressIndicator(
        modifier = Modifier.offset(x = 150.dp, y = (-670).dp),
        isDisplayed = state.value.isLoading
    )
}

@Composable
fun Register(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    onClickAcceptTerms: () -> Unit,
    onClickRegister: (List<String>) -> Unit,
    onClickEnter: () -> Unit
) {
    val context = LocalContext.current
    userRepo = ProtoUserRepoImpl(context.userDataStore)
    val rol: TextFieldValueState = remember { ValueState() }
    val nameState: TextFieldValueState = remember { ValueState() }
    val emailState: TextFieldValueState = remember { EmailState() }
    val passwordState: TextFieldValueState = remember { PasswordState() }
    val passwordConfirmationState: TextFieldValueState = remember { PasswordState() }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        IncomeRegister(modifier = modifier)
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DropDown(
                ValueState = rol, text = "Tipo de rol",
                contentList = listOf("Empresa", "Docente", "Estudiante"),
                icon = Icons.Default.Apartment,
                icon1 = Icons.Default.HistoryEdu,
                icon2 = Icons.Default.School,
                mainIcon = Icons.Default.Groups
            )
            Spacer(modifier = Modifier.size(10.dp))
            NameRegister(name = nameState)
            Spacer(modifier = Modifier.size(10.dp))
            EmailRegister(emailState)
            emailState.getErros()?.let { error -> TextFieldError(textError = error) }
            Spacer(modifier = Modifier.size(10.dp))
            PasswordRegister(passwordState)
            passwordState.getErros()?.let { error -> TextFieldError(textError = error) }
            Spacer(modifier = Modifier.size(10.dp))
            PasswordConfirmation(passwordConfirmationState)
            passwordConfirmationState.getErros()
                ?.let { error -> TextFieldError(textError = error) }
            Spacer(modifier = Modifier.size(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                Arrangement.Center
            ) {
                TextButtonNavigation(text = stringResource(
                    id = R.string.TextButton_You_accept_our_terms_of_use
                ), fontSize = 12.sp, onClick = { onClickAcceptTerms.invoke() })
            }
            Column() {
                ButtonValidation(text = stringResource(R.string.Button_Register_me),
                    onClick = {
                        onClickRegister(
                            listOf(
                                emailState.text,
                                nameState.text,
                                passwordState.text,
                                passwordConfirmationState.text,
                                rol.text
                            )
                        )
                    })
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.Text_Already_have_an_account))
                TextButtonNavigation(text = stringResource(id = R.string.TextButton_Enter),
                    onClick = { onClickEnter.invoke() })
            }
            Spacer(modifier = Modifier.heightIn(min = 8.dp, max = 16.dp))
        }
    }
}

@Composable
fun TextFieldError(textError: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = textError,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = LocalTextStyle.current.copy(color = MaterialTheme.colors.error)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    Register(
        modifier = Modifier,
        rememberScaffoldState(),
        onClickAcceptTerms = { /*TODO*/ },
        onClickRegister = { /*TODO*/ },
        onClickEnter = { /*TODO*/ })
}
