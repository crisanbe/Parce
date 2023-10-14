package com.gerotac.auth.approveanddisapprove.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerotac.auth.approveanddisapprove.domain.usecase.ApproveInterventionCase
import com.gerotac.auth.approveanddisapprove.domain.usecase.DisapproveInterventionCase
import com.gerotac.auth.approveanddisapprove.presentation.state.ApproveAndDisapproveInterventionState
import com.gerotac.auth.updateuser.di.UpdateUserHeaders
import com.gerotac.core.util.UiEvent
import com.gerotac.core.util.UiText
import com.gerotac.shared.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class RefreshViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        doRefresh()
    }

    fun doRefresh() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(3000L)
            _isLoading.value = false
        }
    }
}
