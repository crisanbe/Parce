@file:Suppress("BlockingMethodInNonBlockingContext") @file:OptIn(ExperimentalPermissionsApi::class)

package com.gerotac.auth.intervention.createintervention.presentation.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.text.Html
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gerotac.auth.R
import com.gerotac.auth.dropdownapi.dropdown.presentation.viewmodel.GetApisDropViewModel
import com.gerotac.auth.intervention.createintervention.data.remote.request.SaveInterventionRequest
import com.gerotac.auth.intervention.createintervention.presentation.viewmodel.InterventionViewModel
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.mToast
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.alertdialog.DialogExit
import com.gerotac.components_ui.componets.alertdialog.ViewModelDialog
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.gerotac.components_ui.componets.dropdown.DropString
import com.gerotac.components_ui.componets.progress.LinearProgressBar
import com.gerotac.core.util.UiEvent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.apache.commons.io.FileUtils
import java.io.File

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SaveInterventionScreen(
    navController: NavController,
    code: String,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState, snackbarHost = {
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
            SaveInterventionBody(
                navController = navController,
                code = code,
                scaffoldState
            )
        })
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@Composable
fun SaveInterventionBody(
    navController: NavController,
    code: String,
    scaffoldState: ScaffoldState,
    viewModel: InterventionViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val eventFlow = viewModel.uiEvent.receiveAsFlow()
    val hideKeyboard = LocalSoftwareKeyboardController.current
    var description by remember { mutableStateOf("") }
    var requierement_id by remember { mutableStateOf(1) }
    var type_intervention by remember { mutableStateOf("Tipo de intervención") }
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
                    mToast(activity, "Se agrego el archivo ${temporalFile.name}")
                } else {
                    mToast(activity, "No se agrego el archivo ")
                }
                inputStream?.close()
            }
        }

    DialogExit(text = "Desea volver atrás?😁", onClickYes = {
        showDialog = !showDialog
        navController.navigate(DrawerScreens.CompanyHome.route)
    })
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopPart(onClickAction = { viewModelDialog.showDialog() })
        Text(
            text = stringResource(R.string.TextField_Create_Intervention),
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
                ValueState = { type_intervention = it },
                text = type_intervention,
                options = listOf("Inicial", "Avance", "Final"),
                mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.identity)
            )
            Spacer(Modifier.height(5.dp))
            OutlinedTextField(modifier = Modifier
                .width(350.dp)
                .height(150.dp),
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(id = R.string.TextField_Description)) },
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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            mToast(
                                activity,
                                Html
                                    .fromHtml("<font color='#e3f2fd' ><b>" + "Campo opcional" + "</b></font>")
                                    .toString()
                            )
                        },
                    painter = painterResource(
                        id = com.gerotac.components_ui.R.drawable.info_requirement
                    ),
                    contentDescription = "icono info"
                )
                Spacer(modifier = Modifier.width(5.dp))
                OutlinedButton(
                    modifier = Modifier
                        .widthIn(300.dp)
                        .background(Color(0xFFFFFFFF), CircleShape)
                        .padding(vertical = 20.dp)
                        .shadow(3.dp, CircleShape),
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
                    }
                ) {
                    Icon(
                        Icons.Filled.Upload,
                        contentDescription = "Archivo"
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        "Subir archivo🗂️",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            Button(
                onClick = {
                    hideKeyboard?.hide()
                    scope.launch {
                        viewModel.doSaveIntervention(
                            SaveInterventionRequest(
                                requierement_id = code.toInt(),
                                description = description.toRequestBody("text/plain".toMediaTypeOrNull()),
                                type_intervention = type_intervention.toRequestBody("text/plain".toMediaTypeOrNull()),
                                file = listPdf
                            )
                        )
                        eventFlow.collect { event ->
                            when (event) {
                                is UiEvent.Success -> {
                                    navController.navigate(DrawerScreens.CompanyHome.route)
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
                modifier = Modifier.widthIn(350.dp),
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

    LinearProgressBar(isDisplayed = state.value.isLoading)
}
