package com.parce.auth.requirement.presentation.ui.homerequirement.detail

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import com.parce.auth.R
import com.parce.auth.requirement.domain.model.detailrequirement.DataResponse
import com.parce.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.parce.auth.theme.Dimension
import com.parce.components_ui.componets.TopPart
import com.parce.components_ui.componets.button.ButtonValidation
import com.parce.components_ui.componets.card.CardView
import com.parce.components_ui.componets.drawer.AppScreens
import com.parce.components_ui.componets.drawer.DrawerScreens

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailRequirementViewModel = hiltViewModel(),
    upPress: () -> Unit
) {
    BackHandler(true) { navController.navigate(DrawerScreens.CompanyHome.route) }
    val state = viewModel.state
    DetailContent(
        data = state.detailRequirement,
        upPress = upPress,
        navController = navController
    )
}

@Composable
private fun DetailContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    data: DataResponse?,
    upPress: () -> Unit
) {
    Box(modifier.fillMaxSize()) {
        Column {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                data = data,
                upPress = { upPress() }
            )
            Body(data = data, navController = navController)
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun Header(
    modifier: Modifier = Modifier,
    data: DataResponse?,
    upPress: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(modifier = modifier, scaffoldState = scaffoldState, snackbarHost = {
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
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TopPart(onClickAction = { upPress() })
            Text(
                text = stringResource(R.string.TextField_Requirement_Number) + " #️⃣${data?.id}",
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 55.dp)
            )
        }
    })
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Body(data: DataResponse?, modifier: Modifier = Modifier, navController: NavController) {
    val hideKeyboard = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        FormValueComp(
            ValueState = { data?.areaintervention?.name },
            text = data?.areaintervention?.name.toString(),
            valueText = "Area de intervencion",
            icon = rememberAsyncImagePainter(model = com.parce.components_ui.R.drawable.area)
        )
        OutlinedTextField(modifier = Modifier
            .width(280.dp)
            .wrapContentSize()
            .height(150.dp),
            value = data?.description.toString(),
            onValueChange = { data?.description },
            label = { Text(stringResource(id = R.string.TextField_Description_problem)) },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            maxLines = 5,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { hideKeyboard?.hide() }),
            leadingIcon = {
                Icon(
                    painter = rememberAsyncImagePainter(
                        model = com.parce.components_ui.R.drawable.description
                    ),
                    contentDescription = "",
                )
                Divider(
                    modifier = Modifier
                        .width(34.3.dp)
                        .height(30.dp)
                        .padding(start = 33.dp)
                )
            })
        FormValueComp(
            ValueState = { data?.impact_problem },
            text = data?.impact_problem.toString(),
            valueText = "Impacto del problema",
            icon = rememberAsyncImagePainter(model = com.parce.components_ui.R.drawable.impact)
        )
        FormValueComp(
            ValueState = { data?.efect_problem },
            text = data?.efect_problem.toString(),
            valueText = "Efecto del problema",
            icon = rememberAsyncImagePainter(model = com.parce.components_ui.R.drawable.effect)
        )
        FormValueComp(
            ValueState = { data?.cause_problem },
            text = data?.cause_problem.toString(),
            valueText = "Causa del problema",
            icon = rememberAsyncImagePainter(model = com.parce.components_ui.R.drawable.cause)
        )
        when (data?.user?.role) {
            "empresa" -> { }
            else -> {
                Column() {
                    ButtonValidation(
                        text = "Ver intervenciones",
                        onClick = { navController.navigate(AppScreens.InterventionScreen.route) },
                    )
                }
            }
        }
    }
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .size(36.dp)
    ) {
        Icon(
            imageVector = mirroringBackIcon(),
            tint = Color(0xffffffff),
            contentDescription = null
        )
    }
}

@Composable
fun mirroringBackIcon() = mirroringIcon(
    ltrIcon = Icons.Outlined.ArrowBack, rtlIcon = Icons.Outlined.ArrowForward
)

@Composable
fun mirroringIcon(ltrIcon: ImageVector, rtlIcon: ImageVector): ImageVector =
    if (LocalLayoutDirection.current == LayoutDirection.Ltr) ltrIcon else rtlIcon
