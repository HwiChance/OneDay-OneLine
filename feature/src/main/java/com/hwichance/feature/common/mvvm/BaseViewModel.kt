package com.hwichance.feature.common.mvvm

import androidx.lifecycle.ViewModel
import com.hwichance.feature.common.mvvm.event.UiEvent

abstract class BaseViewModel : ViewModel() {
    abstract fun sendUiEvent(uiEvent: UiEvent)
}
