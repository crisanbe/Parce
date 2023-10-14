package com.gerotac.auth.intervention.getinterventionofdetailrequirement.intervention

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gerotac.auth.approveanddisapprove.presentation.viewmodel.RefreshViewModel
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.AnimationEffect
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.HomeInterventions
import com.gerotac.auth.requirement.presentation.viewmodel.DetailRequirementViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("SuspiciousIndentation")
@Composable
fun InterventionScreen(
    onItemClicked: (Int) -> Unit,
    scaffoldState: ScaffoldState,
    viewModel: DetailRequirementViewModel = hiltViewModel()
) {
    var visible by remember { mutableStateOf(false) }
    val state = viewModel.state
    Card(shape = RoundedCornerShape(20.dp), elevation = 10.dp, backgroundColor = Color(0xFF9DA2A7)) {
        state.detailRequirement?.data?.relations?.interventions?.let {
            InterventionContent(
                onItemClicked = { interventionId -> onItemClicked(interventionId) },
                isLoading = state.isLoading,
                resultIntervention = it,
                scaffoldState = scaffoldState
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
    scaffoldState: ScaffoldState,
    resultIntervention: List<com.gerotac.auth.requirement.domain.model.detailrequirement.Intervention> = ArrayList(),
) {
    val viewModelRefresh = viewModel<DetailRequirementViewModel>()
    val isLoading = viewModelRefresh.state.isLoading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModelRefresh::detailRequirement,
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = Color.Yellow,
                contentColor = Color.DarkGray
            )
        }
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
                        onItemClicked = { onItemClicked(it) },
                        scaffoldState = scaffoldState
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
}