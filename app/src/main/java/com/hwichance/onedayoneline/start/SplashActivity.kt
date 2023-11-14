package com.hwichance.onedayoneline.start

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.hwichance.design.theme.OneDayOneLineTheme
import com.hwichance.feature.login.helper.LoginManager
import com.hwichance.feature.splash.SplashScreen
import com.hwichance.onedayoneline.Navigator
import com.hwichance.util.enums.LoginType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity @Inject constructor() : ComponentActivity() {
    @Inject
    lateinit var loginManager: LoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneDayOneLineTheme {
                SplashScreen()
            }
        }
        checkAlreadyLogin()
    }

    private fun checkAlreadyLogin() {
        lifecycleScope.launch {
            val loginCheck = LoginType.values()
                .map {
                    async { loginManager.isLoggedIn(it) }
                }
                .awaitAll()
            if (loginCheck.any { it }) {
                Navigator.toHome(this@SplashActivity)
            } else {
                Navigator.toLogin(this@SplashActivity)
            }
            finish()
        }
    }
}
