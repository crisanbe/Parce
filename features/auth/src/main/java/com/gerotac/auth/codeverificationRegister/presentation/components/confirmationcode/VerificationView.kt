package com.gerotac.auth.codeverificationRegister.presentation.components.confirmationcode

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gerotac.auth.R
import com.gerotac.auth.codeverificationRegister.data.remote.dto.ParameterCodeConfirmationDto
import com.gerotac.auth.codeverificationRegister.presentation.viewmodel.VerificationCodeViewModel
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.newcode.presentation.viewmodel.NewCodeViewModel
import com.gerotac.components_ui.componets.*
import com.gerotac.components_ui.componets.alertdialog.ViewModelDialog
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun VerificationView(
    navController: NavController,
    viewModelVerificationCode: VerificationCodeViewModel = hiltViewModel(),
    viewModel: NewCodeViewModel = hiltViewModel(),
    valueEmail: String?
) {
    val viemodel: ViewModelDialog = hiltViewModel()
    val scopeVerificationCode = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val stateVerificationCode = viewModelVerificationCode.state.collectAsState()
    val scopeNewCode = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    val stateNewCode = viewModel.state.collectAsState()

    BackHandler(true) { viemodel.showDialog() }
    com.gerotac.components_ui.componets.alertdialog.DialogExit(
        text = "Deseas salir sin verificar correo!🤦‍♂",
        onClickYes = {
            showDialog = !showDialog
            scope.launch {
                userRepo?.getTokenRegister()
                    ?.collect { tokenRegister ->
                        withContext(Dispatchers.Main) {
                            userRepo?.getTokenLoginState()
                                ?.collect { tokenLogin ->
                                    withContext(Dispatchers.Main) {
                                        if (tokenRegister != "" || tokenLogin != "") {
                                            tokenLogin.let { userRepo?.deleteTokenLoginState() }
                                            tokenRegister.let { userRepo?.deleteTokenRegister() }
                                            navController.navigate(AppScreens.StartUp.route)
                                        }
                                    }
                                }
                        }
                    }
            }
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
        ) {
            VerificationCodeView(
                valueEmail,
                onClickVerificationCode = {
                    scope.launch {
                        viewModelVerificationCode.doConfirmationCode(
                            ParameterCodeConfirmationDto(it)
                        )
                        viewModelVerificationCode.uiEvent.collect { event ->
                            when (event) {
                                is UiEvent.Success -> {
                                    userRepo?.getTokenRegister()
                                        ?.collect { tokenRegister ->
                                            withContext(Dispatchers.Main) {
                                                if (tokenRegister != "")
                                                    tokenRegister.let { userRepo?.deleteTokenRegister() }
                                                navController.navigate(
                                                    AppScreens.LoginScreen.route
                                                            + "?email=$valueEmail"
                                                )
                                            }
                                        }
                                    Toast.makeText(
                                        navController.context,
                                        "Success",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                is UiEvent.Error -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Codigo incorrecto!",
                                        actionLabel = "Intentelo nuevamente🤝"
                                    )
                                }
                                else -> Unit
                            }
                        }
                    }
                },
                onClickResendCode = {
                    scopeNewCode.launch {
                        viewModel.doNewCode()
                        viewModel.uiEvent.collect { event ->
                            when (event) {
                                is UiEvent.Success -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Se envio nuevo codigo!",
                                        actionLabel = "Verifica tu correo📧"
                                    )
                                }
                                else -> {}
                            }
                        }
                    }
                }
            )
            CircularIndeterminateProgressBar(stateVerificationCode.value.isLoading)
            CircularIndeterminateProgressBar(stateNewCode.value.isLoading)
        }
    }
}

@Composable
fun VerificationCodeView(
    valueEmail: String?,
    onClickVerificationCode: (String) -> Unit,
    onClickResendCode: () -> Unit
) {
    val length = remember { 4 }
    val viemodel: ViewModelDialog = hiltViewModel()
    var code: List<String> by remember { mutableStateOf(listOf()) }
    val focusRequesters: List<FocusRequester> = remember {
        val temp = mutableListOf<FocusRequester>()
        repeat(length) { temp.add(FocusRequester()) }
        temp
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopPart(onClickAction = { viemodel.showDialog() })
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.Text_Confirmation_code),
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.Text_Enter_the_confirmation_code_received),
                fontSize = 15.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = valueEmail.toString(),
                fontSize = 14.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.Text_Enter_verification_code),
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier.height(50.dp)
            ) {
                (0 until length).forEach { index ->
                    OutlinedTextField(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .focusRequester(focusRequester = focusRequesters[index]),
                        textStyle = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Center),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = stringResource(R.string.OutlinedTextField_X),
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        },
                        value = code.getOrNull(index)?.toString() ?: "",
                        onValueChange = { value: String ->
                            if (focusRequesters[index].freeFocus()) {
                                val temp = code.toMutableList()
                                if (value == "") {
                                    if (temp.size > index) {
                                        temp.removeAt(index)
                                        code = temp
                                        focusRequesters.getOrNull(index - 1)
                                            ?.requestFocus()
                                    }
                                } else {
                                    if (code.size > index) {
                                        temp[index] = value
                                    } else {
                                        temp.add(value)
                                        code = temp
                                        focusRequesters.getOrNull(index + 1)
                                            ?.requestFocus()
                                            ?: onClickVerificationCode(code.joinToString(""))
                                    }
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = { onClickVerificationCode.invoke(code.joinToString("")) },
                shape = RoundedCornerShape(45),
                modifier = Modifier.size(height = 50.dp, width = 300.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Black
                )
            ) {
                Text(
                    text = stringResource(R.string.Button_Enter),
                    modifier = Modifier,
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButtonNavigation(
                text = stringResource(id = R.string.TextButton_Resend_code),
                onClick = { onClickResendCode.invoke() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationCodePreview() {
    VerificationCodeView(
        valueEmail = "******@****.com",
        onClickVerificationCode = { },
        onClickResendCode = {}
    )
}
