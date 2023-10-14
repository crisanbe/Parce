package com.gerotac.components_ui.componets.alertdialog

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ViewModelExitDialog: ViewModel() {
    val isDialogOpen = mutableStateOf(true)
    fun dismissAlertDialog() {
        isDialogOpen.value = true
    }
}
