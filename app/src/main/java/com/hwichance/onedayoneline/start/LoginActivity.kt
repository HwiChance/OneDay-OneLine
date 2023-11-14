package com.hwichance.onedayoneline.start

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.hwichance.design.theme.OneDayOneLineTheme
import com.hwichance.feature.common.mvvm.action.NavigationAction
import com.hwichance.feature.common.mvvm.action.NavigationAction.NavigateToHome
import com.hwichance.feature.login.LoginScreen
import com.hwichance.feature.login.helper.LoginManager
import com.hwichance.feature.login.viewmodel.LoginUiEvent.LoginResultEvent.OnLoginFailure
import com.hwichance.feature.login.viewmodel.LoginUiEvent.LoginResultEvent.OnLoginSuccess
import com.hwichance.feature.login.viewmodel.LoginViewModel
import com.hwichance.onedayoneline.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity @Inject constructor() : ComponentActivity() {
    @Inject
    lateinit var loginManager: LoginManager

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginManager.initialize()
        collectEvent()

        setContent {
            OneDayOneLineTheme {
                LoginScreen()
            }
        }
    }

    private fun collectEvent() {
        loginViewModel.loginTryEventFlow
            .flowWithLifecycle(lifecycle)
            .onEach { type ->
                loginManager.login(
                    type = type,
                    onSuccess = { email ->
                        loginViewModel.sendUiEvent(OnLoginSuccess(email))
                    },
                    onError = {
                        loginViewModel.sendUiEvent(OnLoginFailure(it))
                    },
                )
            }
            .launchIn(lifecycleScope)

        loginViewModel.navigationActionFlow
            .flowWithLifecycle(lifecycle)
            .filterIsInstance<NavigationAction>()
            .onEach { event ->
                if (event == NavigateToHome) {
                    Navigator.toHome(this)
                    finish()
                }
            }
            .launchIn(lifecycleScope)

        loginViewModel.errorEventFlow
            .flowWithLifecycle(lifecycle)
            .onEach {
                Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
            }
            .launchIn(lifecycleScope)
    }

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
