package com.gerotac.auth.reports.presentation.statereports

sealed class FileDownloadScreenState {
    object Idle : FileDownloadScreenState()
    data class Downloading(val progress: Int) : FileDownloadScreenState()
    data class Failed(val error: Throwable? = null) : FileDownloadScreenState()
    object Downloaded : FileDownloadScreenState()
}