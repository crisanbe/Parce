package com.gerotac.auth.reports.presentation.statereports

data class ReportState(
    val isLoading: Boolean = false,
    val error: String = "",
    val message: String = ""
)
