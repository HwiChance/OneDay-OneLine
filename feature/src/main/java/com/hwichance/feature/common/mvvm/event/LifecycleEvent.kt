package com.hwichance.feature.common.mvvm.event

sealed interface LifecycleEvent : UiEvent {
    object OnCreate : LifecycleEvent
    object OnStart : LifecycleEvent
    object OnRestart : LifecycleEvent
    object OnResume : LifecycleEvent
    object OnPause : LifecycleEvent
    object OnStop : LifecycleEvent
    object OnDestroy : LifecycleEvent
}
