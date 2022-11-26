package com.gerotac.auth.requirement.presentation.ui.intervention

data class ViewState(
    val visible: Boolean,
    val onShowRequest: () -> Unit,
    val onDismissRequest: () -> Unit
)