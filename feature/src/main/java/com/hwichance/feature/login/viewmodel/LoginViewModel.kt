package com.hwichance.feature.login.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.hwichance.feature.R
import com.hwichance.feature.common.mvvm.BaseViewModel
import com.hwichance.feature.common.mvvm.action.NavigationAction.NavigateToHome
import com.hwichance.feature.common.mvvm.action.UiAction
import com.hwichance.feature.common.mvvm.event.UiEvent
import com.hwichance.util.enums.LoginType
import com.hwichance.util.exception.EmailNotFoundException
import com.hwichance.util.exception.LoginApiException
import com.hwichance.util.exception.LoginCancelException
import com.hwichance.util.exception.LoginNetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {
    private val _loginTryEventFlow: MutableSharedFlow<LoginType> =
        MutableSharedFlow(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val loginTryEventFlow: SharedFlow<LoginType> get() = _loginTryEventFlow

    private val _navigationActionFlow: MutableSharedFlow<UiAction> =
        MutableSharedFlow(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val navigationActionFlow: SharedFlow<UiAction> get() = _navigationActionFlow

    private val _errorEventFlow: MutableSharedFlow<Int> =
        MutableSharedFlow(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val errorEventFlow: SharedFlow<Int> get() = _errorEventFlow

    override fun sendUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is LoginUiEvent.OnLoginButtonClick -> tryLogin(uiEvent.type)
            is LoginUiEvent.LoginResultEvent.OnLoginSuccess -> saveEmailToServer(uiEvent.email)
            is LoginUiEvent.LoginResultEvent.OnLoginFailure -> handleLoginFailure(uiEvent.exception)
        }
    }

    private fun tryLogin(type: LoginType) {
        viewModelScope.launch {
            _loginTryEventFlow.tryEmit(type)
        }
    }

    private fun saveEmailToServer(email: String) {
        viewModelScope.launch {
            if (email.isNotBlank()) {
                _navigationActionFlow.tryEmit(NavigateToHome)
            }
        }
    }

    private fun handleLoginFailure(exception: Throwable) {
        viewModelScope.launch {
            _errorEventFlow.tryEmit(getErrorMessageResourceId(exception))
        }
    }

    @StringRes
    private fun getErrorMessageResourceId(exception: Throwable): Int {
        return when (exception) {
            EmailNotFoundException -> R.string.login_email_not_found_error
            LoginCancelException -> R.string.login_cancel_error
            LoginApiException -> R.string.login_api_error
            LoginNetworkException -> R.string.login_network_error
            else -> R.string.login_unknown_error
        }
    }
}
