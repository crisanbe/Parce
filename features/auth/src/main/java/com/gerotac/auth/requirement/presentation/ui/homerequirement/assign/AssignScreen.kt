package com.gerotac.auth.requirement.presentation.ui.homerequirement.assign

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.gerotac.auth.R
import com.gerotac.auth.requirement.domain.model.detailrequirement.DataResponse
import com.gerotac.auth.requirement.domain.model.detailrequirement.FileResponse
import com.gerotac.auth.requirement.presentation.ui.homerequirement.detail.FormValueComp
import com.gerotac.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.button.ButtonValidation
import com.gerotac.components_ui.componets.drawer.DrawerScreens

@Composable
fun AssignScreen(
    navController: NavController,
    viewModel: DetailRequirementViewModel = hiltViewModel(),
    upPress: () -> Unit
) {
    BackHandler(true) { navController.navigate(DrawerScreens.CompanyHome.route) }
    val state = viewModel.state
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        AssignContent(
            data = state.detailRequirement,
            upPress = upPress,
            navController = navController
        )
    }
}

@Composable
private fun AssignContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    data: DataResponse?,
    file: FileResponse? = null,
    upPress: () -> Unit
) {
    Box(modifier.fillMaxSize()) {
        Column {
            AssignHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                data = data,
                upPress = { upPress() }
            )
            Spacer(modifier = Modifier.size(20.dp))
            AssignBody(data = data, navController = navController)
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun AssignHeader(
    modifier: Modifier = Modifier,
    data: DataResponse?,
    upPress: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        snackbarHost = {
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
        Column(Modifier.fillMaxSize()) {
            TopPart(onClickAction = { upPress() })
        }
    })
}

@Composable
private fun AssignBody(
    data: DataResponse?,
    file: FileResponse? = null,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val context = LocalContext.current as Activity
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(
            text = stringResource(R.string.TextField_Assign_Requirement) + " #️⃣${data?.id}",
            fontSize = 22.sp,
            color = Color.Black,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        FormValueComp(
            ValueState = { data?.areaintervention?.name.toString() },
            text = data?.areaintervention?.name.toString(),
            valueText = "Seleccionar docente",
            icon = rememberAsyncImagePainter(model = com.gerotac.components_ui.R.drawable.area)
        )
        Spacer(modifier = Modifier.size(50.dp))
        ButtonValidation(text = "Asignar") {}
    }
}
