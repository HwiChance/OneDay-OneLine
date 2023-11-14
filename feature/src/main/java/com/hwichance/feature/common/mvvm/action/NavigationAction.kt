package com.hwichance.feature.common.mvvm.action

sealed interface NavigationAction : UiAction {
    object NavigateToHome : NavigationAction
    object NavigateToLogin : NavigationAction
}
