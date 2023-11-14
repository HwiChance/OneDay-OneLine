package com.hwichance.feature.login.viewmodel

import com.hwichance.feature.common.mvvm.event.UiEvent
import com.hwichance.util.enums.LoginType

sealed interface LoginUiEvent : UiEvent {
    data class OnLoginButtonClick(val type: LoginType) : LoginUiEvent
    sealed interface LoginResultEvent : LoginUiEvent {
        data class OnLoginSuccess(val email: String) : LoginResultEvent
        data class OnLoginFailure(val exception: Throwable) : LoginResultEvent
    }
}
