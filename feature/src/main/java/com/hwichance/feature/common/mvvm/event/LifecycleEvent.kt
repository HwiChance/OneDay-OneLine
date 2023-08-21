package com.hwichance.feature.common.mvvm.event

sealed interface LifecycleEvent : UiEvent {
    object OnResume : LifecycleEvent
}
