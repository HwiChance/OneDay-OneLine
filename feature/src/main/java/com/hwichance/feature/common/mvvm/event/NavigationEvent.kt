package com.hwichance.feature.common.mvvm.event

sealed interface NavigationEvent : UiEvent {
    object NavigateToHome : NavigationEvent
    object NavigateToLogin : NavigationEvent
}
