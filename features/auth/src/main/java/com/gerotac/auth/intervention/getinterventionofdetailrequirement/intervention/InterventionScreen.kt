package com.gerotac.auth.intervention.getinterventionofdetailrequirement.intervention

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gerotac.auth.profileUser.presentation.ui.Drawer
import com.gerotac.auth.requirement.domain.model.getrequirement.Intervention
import com.gerotac.auth.requirement.domain.model.getrequirement.Result
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.AnimationEffect
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.HomeInterventions
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.HomeRequirements
import com.gerotac.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.gerotac.auth.requirement.presentation.viewmodel.RequirementViewModel
import com.gerotac.components_ui.componets.TopBar
import com.gerotac.components_ui.componets.button.TextButtonPersonalized
import com.gerotac.components_ui.componets.drawer.AppScreens
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun InterventionScreen(
    onItemClicked: (Int) -> Unit,
    viewModel: DetailRequirementViewModel = hiltViewModel()
) {
    var visible by remember { mutableStateOf(false) }
    val state = viewModel.state
    Card(shape = RoundedCornerShape(30.dp), elevation = 3.dp, contentColor = Color.Transparent) {
        state.detailRequirement?.data?.relations?.interventions?.let {
            InterventionContent(
                onItemClicked = { interventionId -> onItemClicked(interventionId) },
                isLoading = state.isLoading,
                resultIntervention = it
            )
        }
    }
    LaunchedEffect(visible) {
        visible = true
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun InterventionContent(
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    isLoading: Boolean = false,
    resultIntervention: List<com.gerotac.auth.requirement.domain.model.detailrequirement.Intervention> = ArrayList(),
) {
    Column(modifier = modifier.heightIn(min = 100.dp, max = 500.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                itemsIndexed(
                    items = resultIntervention
                ) { _, resultIntervention ->
                    HomeInterventions(
                        resultInterventions = resultIntervention,
                        onItemClicked = { onItemClicked(it) }
                    )
                }
            }
        )
        Column() {
            repeat(7) {
                if (isLoading) AnimationEffect()
            }
        }
    }
}