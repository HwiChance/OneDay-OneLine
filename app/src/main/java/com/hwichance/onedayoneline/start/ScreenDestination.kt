package com.hwichance.onedayoneline.start

interface ScreenDestination {
    val route: String
}

object SplashScreenDestination : ScreenDestination {
    override val route: String = "start/splash"
}
