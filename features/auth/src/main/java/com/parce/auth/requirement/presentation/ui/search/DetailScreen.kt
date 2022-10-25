package com.parce.auth.requirement.presentation.ui.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.parce.auth.requirement.domain.model.detailrequirement.DataResponse
import com.parce.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.parce.auth.theme.Dimension
import com.parce.components_ui.componets.button.ButtonValidation
import com.parce.components_ui.componets.card.CardView

@Composable
fun DetailScreen(
    viewModel: DetailRequirementViewModel = hiltViewModel(),
    upPress: () -> Unit
) {
    BackHandler(true) {}
    val state = viewModel.state
    DetailContent(
        data = state.detailRequirement,
        upPress = upPress
    )
}

@Composable
private fun DetailContent(
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
                data = data
            )
            Body(data = data)
        }
        Up(upPress)
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    data: DataResponse?
) {
    Column(
        modifier = modifier.background(Color(0xFF21130C)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Requerimiento #..",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
private fun Body(data: DataResponse?) {
    Column(
        modifier = Modifier
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
            onClick = { },
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
