package com.hwichance.feature.splash.viewmodel

import androidx.lifecycle.viewModelScope
import com.hwichance.feature.common.mvvm.BaseViewModel
import com.hwichance.feature.common.mvvm.event.LifecycleEvent
import com.hwichance.feature.common.mvvm.event.NavigationEvent
import com.hwichance.feature.common.mvvm.event.UiEvent
import com.hwichance.feature.login.helper.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginManager: LoginManager,
) : BaseViewModel() {
    private val _navigationEventFlow: MutableSharedFlow<UiEvent> = MutableSharedFlow(replay = 1)
    val navigationEventFlow: SharedFlow<UiEvent> get() = _navigationEventFlow

    override fun sendUiEvent(uiEvent: UiEvent) {
        if (uiEvent == LifecycleEvent.OnResume) {
            checkAlreadyLogIn()
        }
    }

    private fun checkAlreadyLogIn() {
        viewModelScope.launch {
            if (loginManager.isLoggedIn()) {
                _navigationEventFlow.tryEmit(NavigationEvent.NavigateToHome)
            } else {
                _navigationEventFlow.tryEmit(NavigationEvent.NavigateToLogin)
            }
        }
    }
}
