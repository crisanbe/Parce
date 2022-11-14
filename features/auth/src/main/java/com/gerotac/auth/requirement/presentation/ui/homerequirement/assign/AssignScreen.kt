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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.gerotac.auth.R
import com.gerotac.auth.dropdownapi.dropdown.domain.model.listateacher.ResultTeacher
import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.ResultX
import com.gerotac.auth.dropdownapi.dropdown.presentation.ui.DropListTeacher
import com.gerotac.auth.dropdownapi.dropdown.presentation.viewmodel.GetApisDropViewModel
import com.gerotac.auth.requirement.domain.model.detailrequirement.Data
import com.gerotac.auth.requirement.presentation.ui.homerequirement.detail.FormValueComp
import com.gerotac.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.gerotac.components_ui.componets.TopPart
import com.gerotac.components_ui.componets.button.ButtonValidation
import com.gerotac.components_ui.componets.drawer.DrawerScreens

@Composable
fun AssignScreen(
    navController: NavController,
    codeTeacher: String,
    viewModel: DetailRequirementViewModel = hiltViewModel(),
    upPress: () -> Unit
) {
    BackHandler(true) { navController.navigate(DrawerScreens.CompanyHome.route) }
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AssignContent(
            upPress = upPress,
            navController = navController,
            codeTeacher = codeTeacher
        )
    }
}

@Composable
private fun AssignContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    codeTeacher: String,
    upPress: () -> Unit,
    viewModelLocation: GetApisDropViewModel = hiltViewModel()
) {
    val stateGetTeacher = viewModelLocation.stateTeacher.collectAsState()
    Box(modifier.fillMaxSize()) {
        Column {
            AssignHeader(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                upPress = { upPress() }
            )
            Spacer(modifier = Modifier.size(20.dp))
            stateGetTeacher.value.teacherState.let { listTeacher ->
                AssignBody(
                    navController = navController,
                    codeTeacher = codeTeacher,
                    listTeacher = listTeacher
                )
            }
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun AssignHeader(
    modifier: Modifier = Modifier,
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
    listTeacher: List<ResultTeacher> = emptyList(),
    codeTeacher: String,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var selectTeacher by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(
            text = stringResource(R.string.TextField_Assign_Requirement) + " #️⃣${codeTeacher}",
            fontSize = 22.sp,
            color = Color.Black,
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp))
        DropListTeacher(
            selectOptionChange = { selectTeacher = it.toString() },
            text = "Docente",
            options = listTeacher,
            mainIcon = painterResource(id = com.gerotac.components_ui.R.drawable.description)
        )
        Spacer(modifier = Modifier.size(50.dp))
        ButtonValidation(
            text = "Asignar")
        {

        }
    }
}
