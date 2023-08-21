package com.hwichance.feature.common

sealed class ScreenDestination(val route: String) {
    object StartScreenDestination : ScreenDestination("start")
    object SplashScreenDestination : ScreenDestination("start/splash")
    object LoginScreenDestination : ScreenDestination("start/login")

    object HomeScreenDestination : ScreenDestination("home")
}
