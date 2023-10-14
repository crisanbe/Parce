package com.gerotac.auth.reports.presentation.viewmodel

import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.reports.data.api.ReportFileApi
import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.di.createRetrofitApi
import com.gerotac.auth.reports.presentation.statereports.FileDownloadScreenState
import com.gerotac.auth.updateuser.di.UpdateUserHeaders
import com.gerotac.core.util.UiEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File

class ReportFileViewModel : ViewModel() {
    private val api: ReportFileApi = createRetrofitApi()
    var state by mutableStateOf<FileDownloadScreenState>(FileDownloadScreenState.Idle)
        private set
    var uiEvent = Channel<UiEvent>()
        private set

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }

    fun downloadFileCompany(date:ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val timestamp = System.currentTimeMillis()
            api.doReportApi(token.toString(),date)
                .saveFile(timestamp.toString())
                .collect { downloadState ->
                    state = when (downloadState) {
                        is DownloadState.Downloading -> {
                            FileDownloadScreenState.Downloading(progress = downloadState.progress)
                        }
                        is DownloadState.Failed -> {
                            FileDownloadScreenState.Failed(error = downloadState.error)
                        }
                        DownloadState.Finished -> {
                            FileDownloadScreenState.Downloaded
                        }
                    }
                }
        }
    }

    fun downloadFileGender(date:ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val timestamp = System.currentTimeMillis()
            api.doReportGenderApi(token.toString(),date)
                .saveFile(timestamp.toString())
                .collect { downloadState ->
                    state = when (downloadState) {
                        is DownloadState.Downloading -> {
                            FileDownloadScreenState.Downloading(progress = downloadState.progress)
                        }
                        is DownloadState.Failed -> {
                            FileDownloadScreenState.Failed(error = downloadState.error)
                        }
                        DownloadState.Finished -> {
                            FileDownloadScreenState.Downloaded
                        }
                    }
                }
        }
    }

    fun downloadFileDisability(date:ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val timestamp = System.currentTimeMillis()
            api.doReportDisabilityApi(token.toString(),date)
                .saveFile(timestamp.toString())
                .collect { downloadState ->
                    state = when (downloadState) {
                        is DownloadState.Downloading -> {
                            FileDownloadScreenState.Downloading(progress = downloadState.progress)
                        }
                        is DownloadState.Failed -> {
                            FileDownloadScreenState.Failed(error = downloadState.error)
                        }
                        DownloadState.Finished -> {
                            FileDownloadScreenState.Downloaded
                        }
                    }
                }
        }
    }

    fun downloadFileSocieties(date:ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val timestamp = System.currentTimeMillis()
            api.doReportSocietiesApi(token.toString(),date)
                .saveFile(timestamp.toString())
                .collect { downloadState ->
                    state = when (downloadState) {
                        is DownloadState.Downloading -> {
                            FileDownloadScreenState.Downloading(progress = downloadState.progress)
                        }
                        is DownloadState.Failed -> {
                            FileDownloadScreenState.Failed(error = downloadState.error)
                        }
                        DownloadState.Finished -> {
                            FileDownloadScreenState.Downloaded
                        }
                    }
                }
        }
    }

    fun downloadFileInterventionArea(date:ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val timestamp = System.currentTimeMillis()
            api.doReportInterventionAreaApi(token.toString(),date)
                .saveFile(timestamp.toString())
                .collect { downloadState ->
                    state = when (downloadState) {
                        is DownloadState.Downloading -> {
                            FileDownloadScreenState.Downloading(progress = downloadState.progress)
                        }
                        is DownloadState.Failed -> {
                            FileDownloadScreenState.Failed(error = downloadState.error)
                        }
                        DownloadState.Finished -> {
                            FileDownloadScreenState.Downloaded
                        }
                    }
                }
        }
    }

    fun downloadFileEthnicGroup(date:ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val timestamp = System.currentTimeMillis()
            api.doReportEthnicGroupApi(token.toString(),date)
                .saveFile(timestamp.toString())
                .collect { downloadState ->
                    state = when (downloadState) {
                        is DownloadState.Downloading -> {
                            FileDownloadScreenState.Downloading(progress = downloadState.progress)
                        }
                        is DownloadState.Failed -> {
                            FileDownloadScreenState.Failed(error = downloadState.error)
                        }
                        DownloadState.Finished -> {
                            FileDownloadScreenState.Downloaded
                        }
                    }
                }
        }
    }

    fun downloadFileTypeInterventionGroup(date:ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val timestamp = System.currentTimeMillis()
            api.doReportTypeInterventionApi(token.toString(),date)
                .saveFile(timestamp.toString())
                .collect { downloadState ->
                    state = when (downloadState) {
                        is DownloadState.Downloading -> {
                            FileDownloadScreenState.Downloading(progress = downloadState.progress)
                        }
                        is DownloadState.Failed -> {
                            FileDownloadScreenState.Failed(error = downloadState.error)
                        }
                        DownloadState.Finished -> {
                            FileDownloadScreenState.Downloaded
                        }
                    }
                }
        }
    }

    fun downloadFileDepartment(date:ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val timestamp = System.currentTimeMillis()
            api.doReportDepartmentApi(token.toString(),date)
                .saveFile(timestamp.toString())
                .collect { downloadState ->
                    state = when (downloadState) {
                        is DownloadState.Downloading -> {
                            FileDownloadScreenState.Downloading(progress = downloadState.progress)
                        }
                        is DownloadState.Failed -> {
                            FileDownloadScreenState.Failed(error = downloadState.error)
                        }
                        DownloadState.Finished -> {
                            FileDownloadScreenState.Downloaded
                        }
                    }
                }
        }
    }

    fun downloadFileCity(date:ReportRequest) {
        val token = UpdateUserHeaders.getHeader()["Authorization"]
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val timestamp = System.currentTimeMillis()
            api.doReportCityApi(token.toString(),date)
                .saveFile(timestamp.toString())
                .collect { downloadState ->
                    state = when (downloadState) {
                        is DownloadState.Downloading -> {
                            FileDownloadScreenState.Downloading(progress = downloadState.progress)
                        }
                        is DownloadState.Failed -> {
                            FileDownloadScreenState.Failed(error = downloadState.error)
                        }
                        DownloadState.Finished -> {
                            FileDownloadScreenState.Downloaded
                        }
                    }
                }
        }
    }

    fun onIdleRequested() {
        state = FileDownloadScreenState.Idle
    }

    private sealed class DownloadState {
        data class Downloading(val progress: Int) : DownloadState()
        object Finished : DownloadState()
        data class Failed(val error: Throwable? = null) : DownloadState()
    }

    private fun ResponseBody.saveFile(filePostfix: String): Flow<DownloadState> {
        return flow {
            emit(DownloadState.Downloading(0))
            val downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val destinationFile = File(downloadFolder.absolutePath, "reporte_${filePostfix}.pdf")

            try {
                byteStream().use { inputStream ->
                    destinationFile.outputStream().use { outputStream ->
                        val totalBytes = contentLength()
                        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                        var progressBytes = 0L

                        var bytes = inputStream.read(buffer)
                        while (bytes >= 0) {
                            outputStream.write(buffer, 0, bytes)
                            progressBytes += bytes
                            bytes = inputStream.read(buffer)
                            emit(DownloadState.Downloading(((progressBytes * 100) / totalBytes).toInt()))
                        }
                    }
                }
                emit(DownloadState.Finished)
            } catch (e: Exception) {
                emit(DownloadState.Failed(e))
            }
        }
            .flowOn(Dispatchers.IO + coroutineExceptionHandler)
            .distinctUntilChanged()
    }
}
