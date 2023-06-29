package com.hwichance.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class OneDayOneLineColor(
    val bgPrimary: Color = Color.Unspecified,
    val fillPrimary: Color = Color.Unspecified,
    val fillSecondary: Color = Color.Unspecified,
    val fgPrimary: Color = Color.Unspecified,
    val linePrimary: Color = Color.Unspecified,
)

val LocalOneDayOneLineColor = staticCompositionLocalOf { OneDayOneLineColor() }
