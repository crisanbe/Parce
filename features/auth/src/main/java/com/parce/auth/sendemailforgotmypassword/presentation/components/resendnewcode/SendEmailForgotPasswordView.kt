package com.parce.auth.sendemailforgotmypassword.presentation.components.resendnewcode

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.parce.auth.R
import com.parce.auth.sendemailforgotmypassword.data.remote.dto.SendEmailForgotMyPasswordDto
import com.parce.auth.sendemailforgotmypassword.presentation.viewmodel.SendEmailForgotMyPasswordViewModel
import com.parce.components_ui.componets.*
import com.parce.components_ui.componets.button.ButtonValidation
import com.parce.components_ui.componets.drawer.AppScreens
import com.parce.components_ui.componets.progress.LinearProgressBar
import com.parce.core.util.UiEvent
import kotlinx.coroutines.launch

@Composable
fun SendEmailForgotPasswordView(
    navController: NavController,
    viewModel: SendEmailForgotMyPasswordViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState()
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = Color.White,
                    contentColor = Color.Yellow,
                    snackbarData = data,
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(percent = 20),
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
            SendEmailForgotPassword(
                navController = navController,
                onClickActivationCode = { email ->
                    scope.launch {
                        viewModel.doResendNewCode(SendEmailForgotMyPasswordDto(email))
                        viewModel.uiEvent.collect { event ->
                            when (event) {
                                is UiEvent.Success -> {
                                    navController.navigate(
                                        AppScreens.CodeForgotPassword.route
                                                + "?email=$email"
                                    )
                                }
                                else -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Correo incorrecto!",
                                        actionLabel = "Intentelo nuevamente"
                                    )
                                }
                            }
                        }
                    }
                },
                onClickBack = { }
            )
            LinearProgressBar(state.value.isLoading)
        }
    }
}

@Composable
fun SendEmailForgotPassword(
    navController: NavController,
    onClickActivationCode: (String) -> Unit,
    onClickBack: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopPart(onClickAction = { navController.navigate(AppScreens.LoginScreen.route) })
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.Text_Forgot_Your_Password),
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 50.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.Text_To_recover_your_password_you_need),
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 70.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(R.string.Text_Enter_your_email),
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 50.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(R.string.TextField_Email_address)) },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = stringResource(id = R.string.TextField_Email_address),
                    )
                    DividerIcon()
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonValidation(
                text = stringResource(id = R.string.Button_Send_activation_code),
                fontSize = 16.sp,
                onClick = { onClickActivationCode.invoke(email) }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SendEmailForgotPasswordPreview() {
    SendEmailForgotPassword(
        navController = rememberNavController(),
        onClickActivationCode = { },
        onClickBack = {})
}
