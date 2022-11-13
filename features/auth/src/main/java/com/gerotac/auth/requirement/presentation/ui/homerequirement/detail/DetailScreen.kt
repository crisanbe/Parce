package com.gerotac.auth.requirement.presentation.ui.homerequirement.detail

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import com.gerotac.auth.requirement.domain.model.detailrequirement.Result
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.work.*
import coil.compose.rememberAsyncImagePainter
import com.gerotac.auth.R
import com.gerotac.auth.requirement.data.remote.getdetailrequirement.File
import com.gerotac.auth.requirement.di.HeaderRequirement
import com.gerotac.auth.requirement.presentation.ui.homerequirement.detail.dowloadfile.FileDownloadWorker
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.AnimationEffect
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.mToast
import com.gerotac.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.button.ButtonValidation
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@SuppressLint("SuspiciousIndentation")
@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailRequirementViewModel = hiltViewModel(),
    upPress: () -> Unit
) {
    BackHandler(true) { navController.navigate(DrawerScreens.CompanyHome.route) }
    val state = viewModel.state
    DetailContent(
        data = state.detailRequirement,
        upPress = upPress,
        navController = navController
    )
}

@Composable
private fun DetailContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    data: Result?,
    upPress: () -> Unit
) {
    Box(modifier.fillMaxSize()) {
        Column {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                data = data,
                upPress = { upPress() }
            )
            Body(data = data, navController = navController)
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun Header(
    modifier: Modifier = Modifier,
    data: Result?,
    upPress: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(modifier = modifier, scaffoldState = scaffoldState, snackbarHost = {
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
            TopPart(onClickAction = { upPress() })
            Text(
                text = stringResource(R.string.TextField_Requirement_Number) + " #️⃣${data?.id}",
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    })
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalPermissionsApi::class)
@Composable
private fun Body(
    data: Result?,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetailRequirementViewModel = hiltViewModel(),
) {
    val context = LocalContext.current as Activity
    val stateFile = viewModel.state.fileRequirement
    val hideKeyboard = LocalSoftwareKeyboardController.current
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        FormValueComp(
            ValueState = { data?.areaintervention?.name.toString() },
            text = data?.areaintervention?.name.toString(),
            valueText = "Area de intervencion",
            icon = rememberAsyncImagePainter(model = com.gerotac.components_ui.R.drawable.area)
        )
        OutlinedTextField(modifier = Modifier
            .width(280.dp)
            .wrapContentSize()
            .height(90.dp),
            value = data?.description.toString(),
            onValueChange = { data?.description },
            label = { Text(stringResource(id = R.string.TextField_Description_problem)) },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            maxLines = 5,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { hideKeyboard?.hide() }),
            leadingIcon = {
                Icon(
                    painter = rememberAsyncImagePainter(
                        model = com.gerotac.components_ui.R.drawable.description
                    ),
                    tint = Color.Black,
                    contentDescription = "",
                )
                Divider(
                    modifier = Modifier
                        .width(34.3.dp)
                        .height(30.dp)
                        .padding(start = 33.dp)
                )
            })
        FormValueComp(
            ValueState = { data?.impact_problem },
            text = data?.impact_problem.toString(),
            valueText = "Impacto del problema",
            icon = rememberAsyncImagePainter(
                model = com.gerotac.components_ui.R.drawable.impact
            )
        )
        FormValueComp(
            ValueState = { data?.efect_problem },
            text = data?.efect_problem.toString(),
            valueText = "Efecto del problema",
            icon = rememberAsyncImagePainter(
                model = com.gerotac.components_ui.R.drawable.effect
            )
        )
        FormValueComp(
            ValueState = { data?.cause_problem },
            text = data?.cause_problem.toString(),
            valueText = "Causa del problema",
            icon = rememberAsyncImagePainter(
                model = com.gerotac.components_ui.R.drawable.cause
            )
        )
        /*ListFileContent(
            itemFileRequirement = stateFile,
            startDownload = { it },
            openFile = { it }
        )*/

        when (HeaderRequirement.getRol()["rol"]) {
            "estudiante" -> {
                ButtonValidation(text = "Crear intervencion") {
                    navController.navigate(AppScreens.InterventionScreen.route)
                }
            }
            "empresa" -> {
                ButtonValidation(text = "Ver intervenciones") {
                    navController.navigate(AppScreens.InterventionScreen.route)
                }
            }
            else -> {
                ButtonValidation(text = "Ver intervenciones") {
                    navController.navigate(AppScreens.InterventionScreen.route)
                }
                ButtonValidation(text = "Asignar requerimiento") {
                    navController.navigate(AppScreens.AssignScreen.route)
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun ListFileContent(
    modifier: Modifier = Modifier,
    startDownload: (File) -> Unit,
    openFile: (File) -> Unit,
    isLoading: Boolean = false,
    itemFileRequirement: List<File> = ArrayList()
) {
    val context = LocalContext.current as Activity

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        LazyColumn(
            modifier = Modifier.padding(20.dp),
            contentPadding = PaddingValues(horizontal = 1.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                item {
                    Text(
                        text = "Archivos",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(25.dp)
                    )
                }
                itemsIndexed(
                    items = itemFileRequirement
                ) { _, resultRequirements ->
                    ItemFile(
                        file = resultRequirements,
                        startDownload = {
                            startDownload(it)
                            startDownloadingFile(
                                resultRequirements,
                                context = context,
                                success = {
                                    /*resultRequirements.copy().apply {
                                        isDownloading = false
                                        downloadedUri = it
                                    }*/
                                },
                                failed = {
                                    /* resultRequirements.copy().apply {
                                         isDownloading = false
                                         downloadedUri = null
                                     }*/
                                },
                                running = {
                                    /* resultRequirements.copy().apply {
                                         isDownloading = true
                                     }*/
                                }

                            )
                        },
                        openFile = {
                            /*val intent = Intent(Intent.ACTION_VIEW)
                            intent.setDataAndType(it.downloadedUri?.toUri(), "application/pdf")
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)*/
                            try {
                                //context.startActivity(intent)
                            } catch (e: ActivityNotFoundException) {
                                mToast(context, "$e")
                            }
                        })
                }
            }
        )
        Column() {
            repeat(7) {
                if (isLoading) AnimationEffect()
            }
        }
    }
}

@Composable
fun ItemFile(
    file: File,
    startDownload: (File) -> Unit,
    openFile: (File) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(25.dp))
            .clickable {
                /* if (!file.isDownloading) {
                     if (file.url.isEmpty()) {
                         startDownload(file)
                     } else {
                         openFile(file)
                     }
                 }*/
            }
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Text(
                    text = file.url,
                    color = Color.Black
                )

                Row {
                    /*val description = if (file.isDownloading) {
                        "Downloading..."
                    } else {
                        if (file.downloadedUri.isNullOrEmpty()) "download the file" else "Tap to open file"
                    }*/
                    Text(
                        text = "description",
                        color = Color.DarkGray
                    )
                }

            }

            /*if (file.isDownloading) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterVertically)
                )
            }*/

        }

    }
}

private fun startDownloadingFile(
    file: File,
    context: Context,
    success: (String) -> Unit,
    failed: (String) -> Unit,
    running: () -> Unit
) {
    val data = Data.Builder()

    data.apply {
        putString(FileDownloadWorker.FileParams.KEY_FILE_NAME, file.created_at)
        putString(FileDownloadWorker.FileParams.KEY_FILE_URL, file.url)
    }

    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresStorageNotLow(true)
        .setRequiresBatteryNotLow(true)
        .build()

    val fileDownloadWorker = OneTimeWorkRequestBuilder<FileDownloadWorker>()
        .setConstraints(constraints)
        .setInputData(data.build())
        .build()

    val workManager = WorkManager.getInstance(context)
    workManager.enqueueUniqueWork(
        "oneFileDownloadWork_${System.currentTimeMillis()}",
        ExistingWorkPolicy.KEEP,
        fileDownloadWorker
    )

    workManager.getWorkInfoByIdLiveData(fileDownloadWorker.id)
        .observe(context as LifecycleOwner) { info ->
            info?.let {
                when (it.state) {
                    WorkInfo.State.SUCCEEDED -> {
                        success(
                            it.outputData.getString(FileDownloadWorker.FileParams.KEY_FILE_URI)
                                ?: ""
                        )
                    }
                    WorkInfo.State.FAILED -> {
                        failed("Downloading failed!")
                    }
                    WorkInfo.State.RUNNING -> {
                        running()
                    }
                    else -> {
                        failed("Something went wrong")
                    }
                }
            }
        }
}
/*OutlinedButton(
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

                    }
                    !perm.status.isGranted -> {
                        navController.navigate(AppScreens.PermissionScreen.route)
                    }
                }
            }
        }
    }
}
){
    Icon(
        Icons.Filled.Upload,
        contentDescription = "Descarga"
    )
    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
    Text("Descargar archivo🗂️",
        fontSize = 15.sp,
        fontWeight = FontWeight.ExtraBold
    )
}*/
