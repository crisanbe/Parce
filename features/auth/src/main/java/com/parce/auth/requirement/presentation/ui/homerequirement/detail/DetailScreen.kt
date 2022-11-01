package com.parce.auth.requirement.presentation.ui.homerequirement.detail

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.parce.auth.R
import com.parce.auth.requirement.domain.model.detailrequirement.DataResponse
import com.parce.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.parce.auth.theme.Dimension
import com.parce.components_ui.componets.TopPart
import com.parce.components_ui.componets.button.ButtonValidation
import com.parce.components_ui.componets.card.CardView
import com.parce.components_ui.componets.drawer.DrawerScreens

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailRequirementViewModel = hiltViewModel(),
    upPress: () -> Unit,
    id: String,
) {
    BackHandler(true) { navController.navigate(DrawerScreens.CompanyHome.route) }
    val state = viewModel.state
    DetailContent(
        data = state.detailRequirement,
        upPress = upPress,
        id = id
    )
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    data: DataResponse?,
    id: String? = null,
    upPress: () -> Unit
) {
    Box(modifier.fillMaxSize()) {
        Column {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                data = data,
                upPress = { upPress() },
                id = id.toString()
            )
            Body(data = data)
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun Header(
    modifier: Modifier = Modifier,
    id: String,
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
                text = stringResource(R.string.TextField_Requirement_Number) + " #️⃣$id",
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 55.dp)
            )
            Text(
                text = data?.description ?: "",
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
        }
    })
}


@Composable
private fun Body(data: DataResponse?, modifier: Modifier = Modifier) {
    val counter by remember { mutableStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(Dimension.pagePadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        CardView(
            item = data?.impact_problem ?: "N",
            elevation = 10.dp,
            text = "Tipo de documento: ",
            border = BorderStroke(1.dp, Color.Black)
        )
        CardView(
            item = data?.description ?: "T",
            elevation = 10.dp,
            text = "Tipo de documento: ",
            border = BorderStroke(1.dp, Color.Black)
        )
        CardView(
            item = data?.cause_problem ?: "R",
            elevation = 10.dp,
            text = "Tipo de documento: ",
            border = BorderStroke(1.dp, Color.Black)
        )
        CardView(
            item = data?.efect_problem ?: "e",
            elevation = 10.dp,
            text = "Tipo de documento: ",
            border = BorderStroke(1.dp, Color.Black)
        )
        CardView(
            item = data?.description ?: "f",
            elevation = 10.dp,
            text = "Tipo de documento: ",
            border = BorderStroke(1.dp, Color.Black)
        )
        ButtonValidation(
            text = "Asignar",
            onClick = { println(counter) },
        )
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
