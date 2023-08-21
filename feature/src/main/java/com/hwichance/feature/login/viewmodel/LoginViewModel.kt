package com.hwichance.feature.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.hwichance.feature.R
import com.hwichance.feature.common.mvvm.BaseViewModel
import com.hwichance.feature.common.mvvm.event.NavigationEvent
import com.hwichance.feature.common.mvvm.event.UiEvent
import com.hwichance.feature.login.helper.LoginManager
import com.hwichance.util.enums.EmailNotFoundException
import com.hwichance.util.enums.LoginApiException
import com.hwichance.util.enums.LoginCancelException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginManager: LoginManager,
) : BaseViewModel() {
    private val _navigationEventFlow: MutableSharedFlow<UiEvent> = MutableSharedFlow(replay = 1)
    val navigationEventFlow: SharedFlow<UiEvent> get() = _navigationEventFlow

    private val _errorEventFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)
    val errorEventFlow: SharedFlow<Int> get() = _errorEventFlow

    override fun sendUiEvent(uiEvent: UiEvent) {
        when (uiEvent) {
            is LoginUiEvent.LoginResultEvent.OnLoginSuccess -> tryGoogleLogin(uiEvent.email)
            is LoginUiEvent.LoginResultEvent.OnLoginFailure -> handleLoginFailure(uiEvent.exception)
        }
    }

    private fun tryGoogleLogin(email: String) {
        viewModelScope.launch {
            println("tryGoogleLogin : $email")
            if (email.isNotBlank()) {
                _navigationEventFlow.tryEmit(NavigationEvent.NavigateToHome)
            }
        }
    }

    private fun handleLoginFailure(exception: Exception) {
        viewModelScope.launch {
            val errorMessage = when (exception) {
                EmailNotFoundException -> R.string.login_email_not_found_error
                LoginCancelException -> R.string.login_cancel_error
                LoginApiException -> R.string.login_api_error
                else -> R.string.login_unknown_error
            }
            _errorEventFlow.tryEmit(errorMessage)
        }
    }
}
