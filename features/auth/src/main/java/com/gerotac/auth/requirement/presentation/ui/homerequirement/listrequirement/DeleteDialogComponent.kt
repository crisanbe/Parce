package com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.gerotac.auth.login.presentation.components.logincomposables.userRepo
import com.gerotac.auth.requirement.presentation.viewmodel.DeleteRequirementViewModel
import com.gerotac.components_ui.componets.alertdialog.ExitAlertDialog
import com.gerotac.components_ui.componets.drawer.AppScreens
import com.gerotac.components_ui.componets.drawer.DrawerScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun DeleteDialog(
    navController: NavController,
    id: String,
    viewModel: DeleteRequirementViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://cdn-icons-png.flaticon.com/512/3687/3687412.png")
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 100.dp)
                .clip(CircleShape)
        )
        ExitAlertDialog(
            text = "Estas seguro, que quieres eliminar el requerimiento #${id}?😉",
            onClickYes = {
                showDialog = !showDialog
                viewModel.doUpdateRequirement(id.toInt())
                navController.navigate(DrawerScreens.CompanyHome.route)
            },
            onClickNot = {
                navController.navigate(DrawerScreens.CompanyHome.route)
            }
        )
    }

}