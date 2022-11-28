@file:Suppress("UselessCallOnNotNull")

package com.gerotac.auth.intervention.detailintervention.presentation.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.work.*
import coil.compose.rememberAsyncImagePainter
import com.gerotac.auth.R
import com.gerotac.auth.approveanddisapprove.presentation.ui.CheckedApproveAndDisapprove
import com.gerotac.auth.intervention.detailintervention.domain.model.File
import com.gerotac.auth.intervention.detailintervention.domain.model.DetailResponseIntervention
import com.gerotac.auth.intervention.detailintervention.presentation.viewmodel.DetailInterventionViewModel
import com.gerotac.auth.intervention.getinterventionofdetailrequirement.intervention.MenuInferiorViewModel
import com.gerotac.auth.intervention.getinterventionofdetailrequirement.intervention.component.DialogContent
import com.gerotac.auth.requirement.di.HeaderRequirement
import com.gerotac.auth.requirement.presentation.ui.homerequirement.detail.FormValueComp
import com.gerotac.auth.requirement.presentation.ui.homerequirement.detail.dowloadfile.FileDownloadWorker
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.AnimationEffect
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.mToast
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.button.BottomSheetDialog
import com.gerotac.components_ui.componets.button.ButtonValidation
import com.gerotac.components_ui.componets.button.ButtonWithShadow
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailInterventionScreen(
    navController: NavController,
    upPress: () -> Unit,
    viewModel: DetailInterventionViewModel = hiltViewModel(),
    viewModelLowerMenu: MenuInferiorViewModel = hiltViewModel(),
) {
    BackHandler(true) { navController.navigate(DrawerScreens.CompanyHome.route) }
    val state = viewModel.state
    val stateFile = viewModel.state.fileIntervention
    val viewState by viewModelLowerMenu.viewState.collectAsState()
    val activity = LocalContext.current as Activity
    WindowCompat.setDecorFitsSystemWindows(activity.window, true)
    Scaffold(
        backgroundColor = Color(0xFFFFFFFF),
        modifier = Modifier,
        topBar = { TopPart(onClickAction = { upPress() }) },
        bottomBar = {
            BottomSheetDialog(
                modifier = Modifier.background(Color(0xFF353432)),
                visible = viewState.visible,
                cancelable = true,
                canceledOnTouchOutside = true,
                onDismissRequest = viewState.onDismissRequest
            ) {
                DialogContent(onDismissRequest = { viewState.onDismissRequest })
                {
                    if (!state.fileIntervention.isNullOrEmpty()) {
                        Text(text = "Archivos", fontWeight = FontWeight.Bold)
                        ListFileContent(
                            itemFileIntervention = stateFile
                        )
                    } else {
                        Text(text = "No hay, archivos!", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ) {
            Body(data = state.intervention, navController = navController)

        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun Body(
    data: DetailResponseIntervention?,
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModelLowerMenu: MenuInferiorViewModel = hiltViewModel(),

    ) {
    val viewState by viewModelLowerMenu.viewState.collectAsState()
    val protoTypeIntervention: MutableStateFlow<String> =
        MutableStateFlow(data?.data?.type_intervention.toString())
    var typeIntervention = protoTypeIntervention.collectAsState().value
    val context = LocalContext.current as Activity
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = stringResource(R.string.TextField_Intervention) +
                    " #️⃣${data?.data?.id} del requerimiento #️⃣${data?.data?.requierement}",
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        FormValueComp(
            ValueState = { typeIntervention = it },
            text = typeIntervention,
            valueText = stringResource(R.string.TextField_Type_intervention),
            icon = rememberAsyncImagePainter(model = com.gerotac.components_ui.R.drawable.area)
        )
        androidx.compose.material.OutlinedTextField(
            modifier = Modifier
                .width(280.dp)
                .wrapContentSize()
                .height(90.dp),
            value = data?.data?.description ?: "",
            onValueChange = { data?.data?.description },
            label = { Text(stringResource(id = R.string.TextField_Description)) },
            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            maxLines = 5,
            readOnly = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
            ValueState = { data?.data?.user?.name },
            text = data?.data?.user?.name,
            valueText = "Usuario",
            icon = rememberAsyncImagePainter(
                model = com.gerotac.components_ui.R.drawable.identity
            )
        )
        FormValueComp(
            ValueState = { data?.data?.user?.role },
            text = data?.data?.user?.role,
            valueText = "Rol",
            icon = rememberAsyncImagePainter(
                model = com.gerotac.components_ui.R.drawable.user
            )
        )
        Spacer(modifier = Modifier.height(18.dp))

         if (HeaderRequirement.getRol()["rol"] == "empresa" ||
             HeaderRequirement.getRol()["rol"] == "docente") {
            //TODO this is the intervention id
            if (!data?.data?.id.toString().isNullOrEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .widthIn(300.dp)
                            .background(Color(0xFFFFFFFF), CircleShape)
                            .padding(vertical = 20.dp)
                            .shadow(3.dp, CircleShape),
                        onClick = {
                            viewState.onShowRequest()
                        }
                    ) {
                        Icon(
                            Icons.Filled.Archive,
                            contentDescription = "Archivo"
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            "Ver archivo🗂️",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun ListFileContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    itemFileIntervention: List<File> = ArrayList()
) {
    val context = LocalContext.current as Activity
    Surface(
        modifier = modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(30.dp),
        color = Color(0xFFE4E5E6)
    ) {
        LazyColumn(
            modifier = Modifier.padding(5.dp),
            contentPadding = PaddingValues(horizontal = 5.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                itemsIndexed(
                    items = itemFileIntervention
                ) { _, resultArchives ->
                    ItemFile(
                        file = resultArchives,
                        startDownload = {
                            startDownloadingFile(
                                resultArchives,
                                context = context,
                                success = {
                                    resultArchives.copy().apply {
                                        isDownloading = false
                                        downloadedUri = it
                                    }
                                },
                                failed = {
                                    resultArchives.copy().apply {
                                        isDownloading = false
                                        downloadedUri = null
                                    }
                                },
                                running = {
                                    resultArchives.copy().apply {
                                        isDownloading = true
                                    }
                                }
                            )
                        },
                        openFile = {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.setDataAndType(
                                "https://parces.gerotac.com/${it.url}".toUri(),
                                "application/pdf"
                            )
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            try {
                                context.startActivity(intent)
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
            .background(color = Color(0xFFFFFAFA))
            .border(width = 3.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
            .clickable {
                if (!file.isDownloading) {
                    if (file.url.isEmpty()) {
                        startDownload(file)
                    } else {
                        openFile(file)
                    }
                }
            }
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    text = file.filename,
                    color = Color.Black
                )
                Row {
                    val description = if (file.isDownloading) {
                        "Descargando..."
                    } else {
                        if (file.url.isNullOrEmpty()) "Descargar archivo🗃️" else "Pulse para abrir el archivo👆"
                    }
                    Text(
                        text = description,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            if (file.isDownloading) {
                CircularProgressIndicator(
                    color = Color.Blue,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterVertically)
                )
            }
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
    val data = androidx.work.Data.Builder()

    data.apply {
        putString(FileDownloadWorker.FileParams.KEY_FILE_NAME, file.created_at)
        putString(FileDownloadWorker.FileParams.KEY_FILE_URL, file.url)
        putString(FileDownloadWorker.FileParams.KEY_FILE_URL, file.filename)
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
