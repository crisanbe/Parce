package com.parce.auth.newpasswordforget.presentation.components.newpassword

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parce.auth.R
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.auth.newpasswordforget.data.remote.dto.NewPasswordForgetDto
import com.parce.auth.newpasswordforget.presentation.viewmodel.NewPasswordForgetViewModel
import com.parce.components_ui.componets.*
import com.parce.components_ui.componets.alertdialog.ViewModelDialog
import com.parce.components_ui.componets.button.ButtonValidation
import com.parce.components_ui.componets.drawer.AppScreens
import com.parce.core.util.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NewPasswordForgetView(
    navController: NavController,
    valueToken: String?,
    viewModel: NewPasswordForgetViewModel = hiltViewModel()
) {
    val viemodel: ViewModelDialog = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState()
    val coroutine = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    BackHandler(true) { viemodel.showDialog() }
    com.parce.components_ui.componets.alertdialog.DialogExit(
        text = "Deseas salir sin confirmar tu contraseña?🔑",
        onClickYes = {
            showDialog = !showDialog
            navController.navigate(AppScreens.StartUp.route)
        }
    )
    Scaffold(
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
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            NewPasswordForget(
                onClickConfirmPassword = { password ->
                    coroutine.launch {
                        viewModel.doNewPasswordForget(
                            NewPasswordForgetDto(
                                token = valueToken.toString(),
                                password = password,
                                confirmed_password = password
                            )
                        )
                        viewModel.uiEvent.collect { event ->
                            when (event) {
                                is UiEvent.Success -> {
                                    userRepo?.getEmailUser()?.collect { email ->
                                        withContext(Dispatchers.Main) {
                                            navController.navigate(
                                                AppScreens.LoginScreen.route
                                                        + "?email=$email"
                                            )
                                        }
                                    }
                                }
                                else -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = state.value.error,
                                        actionLabel = "Intentelo nuevamente"
                                    )
                                }
                            }
                        }
                    }
                }
            )
            CircularIndeterminateProgressBar(isDisplayed = state.value.isLoading)
        }
    }
}

@Composable
fun NewPasswordForget(
    onClickConfirmPassword: (String) -> Unit,
) {
    val viemodel: ViewModelDialog = hiltViewModel()
    var password by remember { mutableStateOf("") }
    var hidden by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopPart(onClickAction = { viemodel.showDialog() })
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.Text_Forgot_password),
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 50.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.Text_Please_enter_your_new_password),
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 70.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.Text_Enter_new_password),
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 50.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { stringResource(R.string.TextField_Password) },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = stringResource(R.string.TextField_Password)
                    )
                    DividerIcon()
                },
                visualTransformation =
                if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = { hidden = !hidden }) {
                        if (hidden) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = null,
                                tint = Color.Black,
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = Color.Black,
                            )
                        }
                    }
                },
                modifier = Modifier
                    .width(300.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { stringResource(R.string.TextField_Password) },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = stringResource(R.string.TextField_Password)
                    )
                    DividerIcon()
                },
                visualTransformation =
                if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = { hidden = !hidden }) {
                        if (hidden) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = null,
                                tint = Color.Black,
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = Color.Black,
                            )
                        }
                    }
                },
                modifier = Modifier
                    .width(300.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonValidation(
                text = stringResource(R.string.Button_Confirm_password),
                onClick = { onClickConfirmPassword.invoke(password) }
            )
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewPasswordForget() {
    NewPasswordForget(onClickConfirmPassword = {})
}
