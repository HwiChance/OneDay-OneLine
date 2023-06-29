package com.hwichance.design.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object OneDayOneLineTheme {
    val colors: OneDayOneLineColor
        @Composable
        @ReadOnlyComposable
        get() = LocalOneDayOneLineColor.current

    val typography: OneDayOneLineTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalOneDayOneLineTypography.current

    val shape: OneDayOneLineShape
        @Composable
        @ReadOnlyComposable
        get() = LocalOneDayOneLineShape.current
}

@Composable
fun OneDayOneLineTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val oneDayOneLineColor = if (darkTheme) darkThemeColorScheme else lightThemeColorScheme

    CompositionLocalProvider(
        LocalOneDayOneLineColor provides oneDayOneLineColor,
        LocalOneDayOneLineTypography provides themeTypoScheme,
        LocalOneDayOneLineShape provides themeShapeScheme,
    ) {
        MaterialTheme(
            colors = if (darkTheme) materialDarkColorScheme else materialLightColorScheme,
            content = content,
        )
    }
}
