package com.hwichance.feature.login.viewmodel

import com.hwichance.feature.common.mvvm.event.UiEvent

sealed interface LoginUiEvent : UiEvent {
    sealed interface LoginResultEvent : LoginUiEvent {
        data class OnLoginSuccess(val email: String) : LoginResultEvent
        data class OnLoginFailure(val exception: Exception) : LoginResultEvent
    }
}
