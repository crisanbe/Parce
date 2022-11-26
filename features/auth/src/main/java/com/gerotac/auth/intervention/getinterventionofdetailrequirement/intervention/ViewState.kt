package com.gerotac.auth.intervention.getinterventionofdetailrequirement.intervention

data class ViewState(
    val visible: Boolean,
    val onShowRequest: () -> Unit,
    val onDismissRequest: () -> Unit
)