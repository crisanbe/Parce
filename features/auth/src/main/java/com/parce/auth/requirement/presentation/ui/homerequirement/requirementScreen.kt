@file:Suppress("BlockingMethodInNonBlockingContext") @file:OptIn(ExperimentalPermissionsApi::class)

package com.parce.auth.requirement.presentation.ui.homerequirement

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Feed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.*
import com.parce.auth.R
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.auth.requirement.data.remote.requirement.RequirementRequest
import com.parce.auth.requirement.presentation.viewmodel.RequirementViewModel
import com.parce.components_ui.componets.TopPart
import com.parce.components_ui.componets.alertdialog.DialogExit
import com.parce.components_ui.componets.alertdialog.ViewModelDialog
import com.parce.components_ui.componets.drawer.AppScreens
import com.parce.components_ui.componets.drawer.DrawerScreens
import com.parce.components_ui.componets.dropdown.DropString
import com.parce.components_ui.componets.progress.ProgressIndicator
import com.parce.core.util.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.apache.commons.io.FileUtils
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun RequirementScreen(
    navController: NavController,
    viewModel: RequirementViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val eventFlow = viewModel.uiEvent.receiveAsFlow()
    val statePages = viewModel.statePages.collectAsState()
    val state = viewModel.state.collectAsState()
    val hideKeyboard = LocalSoftwareKeyboardController.current
    var description by remember { mutableStateOf("descrip") }
    var interventionArea by remember { mutableStateOf(1) }
    var causeOfTheProblem by remember { mutableStateOf("cause") }
    var effectsOfTheProblem by remember { mutableStateOf("efect") }
    var impactOfTheProblem by remember { mutableStateOf("Impact") }
    val viewModelDialog: ViewModelDialog = hiltViewModel()
    var showDialog by remember { mutableStateOf(false) }
    val listPdf: MutableList<MultipartBody.Part> = mutableListOf()
    BackHandler(true) { viewModelDialog.showDialog() }

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    )


    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            it.forEach { item ->
                val inputStream = activity.contentResolver.openInputStream(item)
                val temporalFile = File.createTempFile("file", ".pdf")
                FileUtils.copyToFile(inputStream, temporalFile)
                if (temporalFile.exists()) {
                    listPdf.add(
                        MultipartBody.Part.createFormData(
                            "document[]",
                            temporalFile.path,
                            temporalFile.asRequestBody("application/pdf".toMediaTypeOrNull())
                        )
                    )
                    mToast(activity,"Se agrego el archivo ${temporalFile.toURI()}")
                } else {
                    mToast(activity,"Se agrego el archivo ")
                }
                inputStream?.close()
            }
        }

    DialogExit(text = "Deseas salir sin crear el requerimiento!😁", onClickYes = {
        showDialog = !showDialog
        navController.navigate(DrawerScreens.CompanyHome.route)
    })
    Scaffold(modifier = Modifier, scaffoldState = scaffoldState, snackbarHost = {
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
                .verticalScroll(rememberScrollState())
        ) {
            TopPart(onClickAction = { viewModelDialog.showDialog() })
            Text(
                text = stringResource(R.string.TextField_Create_Requirement),
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 55.dp)
                    .offset(y = (-30).dp)
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .offset(y = (-15).dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                DropString(
                    ValueState = { interventionArea = it.toInt() },
                    text = "Area de intervencion",
                    options = listOf("1"),
                    mainIcon = painterResource(id = com.parce.components_ui.R.drawable.identity)
                )
                Spacer(Modifier.height(5.dp))
                OutlinedTextField(modifier = Modifier
                    .width(280.dp)
                    .wrapContentSize()
                    .height(150.dp),
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(stringResource(id = R.string.TextField_Description_problem)) },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    maxLines = 5,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { hideKeyboard?.hide() }),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "",
                        )
                        Divider(
                            modifier = Modifier
                                .width(34.3.dp)
                                .height(30.dp)
                                .padding(start = 33.dp)
                        )
                    })
                Spacer(Modifier.height(5.dp))
                TextField(value = causeOfTheProblem,
                    onValueChange = { causeOfTheProblem = it },
                    label = { Text(stringResource(id = R.string.TextField_causas_problems)) },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { hideKeyboard?.hide() }),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Feed,
                            contentDescription = "",
                        )
                        Divider(
                            modifier = Modifier
                                .width(34.3.dp)
                                .height(30.dp)
                                .padding(start = 33.dp)
                        )
                    })
                Spacer(Modifier.height(5.dp))
                TextField(value = effectsOfTheProblem,
                    onValueChange = { effectsOfTheProblem = it },
                    label = { Text(stringResource(id = R.string.TextField_efect_problems)) },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { hideKeyboard?.hide() }),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Feed,
                            contentDescription = "",
                        )
                        Divider(
                            modifier = Modifier
                                .width(34.3.dp)
                                .height(30.dp)
                                .padding(start = 33.dp)
                        )
                    })
                Spacer(Modifier.height(5.dp))
                TextField(value = impactOfTheProblem,
                    onValueChange = { impactOfTheProblem = it },
                    label = { Text(stringResource(id = R.string.TextField_impact_problems)) },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { hideKeyboard?.hide() }),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Feed,
                            contentDescription = "",
                        )
                        Divider(
                            modifier = Modifier
                                .width(34.3.dp)
                                .height(30.dp)
                                .padding(start = 33.dp)
                        )
                    })
                Spacer(Modifier.height(15.dp))
                Button(
                    onClick = {
                        permissionsState.permissions.forEach { perm ->
                            when (perm.permission) {
                                Manifest.permission.READ_EXTERNAL_STORAGE -> {
                                    when {
                                        perm.status.isGranted -> {
                                            launcher.launch("application/pdf")
                                        }
                                        !perm.status.isGranted -> {
                                            navController.navigate(AppScreens.PermissionScreen.route)
                                        }
                                    }
                                }
                            }
                        }
                    },
                    contentPadding = PaddingValues(
                        start = 20.dp,
                        top = 12.dp,
                        end = 20.dp,
                        bottom = 12.dp
                    )
                ) {
                    Icon(
                        Icons.Filled.Upload,
                        contentDescription = "Archivo",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Subir archivo🗂️")
                }
                Spacer(Modifier.height(15.dp))
                Button(
                    onClick = {
                        hideKeyboard?.hide()
                        scope.launch {
                            viewModel.doRequirement(
                                RequirementRequest(
                                    area_intervention = interventionArea,
                                    description = description,
                                    cause_problem = causeOfTheProblem,
                                    efect_problem = causeOfTheProblem,
                                    impact_problem = impactOfTheProblem,
                                    file = listPdf
                                )
                            )
                            eventFlow.collectLatest { event ->
                                when (event) {
                                    is UiEvent.Success -> {
                                        viewModel.doGetPagination()
                                        val pages = statePages.value.pagination?.last_page
                                        userRepo?.savePages((pages))
                                        navController.navigate(
                                            DrawerScreens.CompanyHome.route
                                        )
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Se guardo exitosamente🏅",
                                            actionLabel = "Continue"
                                        )
                                    }
                                    is UiEvent.ShowSnackBar -> {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = event.message.asString(activity)
                                        )
                                    }
                                    else -> Unit
                                }
                            }
                        }
                    },
                    shape = RoundedCornerShape(percent = 45),
                    modifier = Modifier.size(height = 55.dp, width = 300.dp),
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
                ) {
                    Text(
                        text = stringResource(R.string.Button_Save),
                        modifier = Modifier,
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    })
    ProgressIndicator(
        modifier = Modifier.offset(x = 150.dp, y = (-790).dp), isDisplayed = state.value.isLoading
    )
}

private fun readTextFromUri(context: Context, uri: Uri) {
    try {
        val stringBuilder = StringBuilder()
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line: String? = reader.readLine()
                while (line != null) {
                    stringBuilder.append(line)
                    line = reader.readLine()
                }
            }
        }
        val text = stringBuilder.toString()
        Log.d("xxx", "text $text")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

private fun uriPath(uri: Uri): String {
    val file = File(uri.path.toString())
    val absolutePath = file.absolutePath
    return absolutePath.substring(absolutePath.indexOf(":") + 1)
}
