package com.hwichance.onedayoneline.start

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hwichance.feature.common.ScreenDestination.LoginScreenDestination
import com.hwichance.feature.common.ScreenDestination.SplashScreenDestination
import com.hwichance.feature.common.ScreenDestination.StartScreenDestination
import com.hwichance.feature.login.LoginScreen
import com.hwichance.feature.splash.SplashScreen

@Composable
fun StartNavHost(
    navigateToHome: () -> Unit,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SplashScreenDestination.route,
        route = StartScreenDestination.route,
    ) {
        composable(route = SplashScreenDestination.route) {
            SplashScreen(navController = navController, navigateToHome = navigateToHome)
        }
        composable(route = LoginScreenDestination.route) {
            LoginScreen(navigateToHome = navigateToHome)
        }
    }
}
