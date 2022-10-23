package com.parce.components_ui.componets.alertdialog

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ViewModelDialog: ViewModel() {
    val isDialogOpen = mutableStateOf(false)
    fun showDialog() {
        isDialogOpen.value = true
    }
    fun dismissDialog() {
        isDialogOpen.value = false
    }
}