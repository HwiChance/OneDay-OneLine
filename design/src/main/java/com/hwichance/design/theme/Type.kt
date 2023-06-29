package com.hwichance.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class OneDayOneLineTypography(
    val h1: TextStyle = TextStyle(),
    val h2: TextStyle = TextStyle(),
    val h3: TextStyle = TextStyle(),
    val h4: TextStyle = TextStyle(),
    val h5: TextStyle = TextStyle(),
    val h6: TextStyle = TextStyle(),
    val subtitle1: TextStyle = TextStyle(),
    val subtitle2: TextStyle = TextStyle(),
    val body1: TextStyle = TextStyle(),
    val body2: TextStyle = TextStyle(),
    val caption: TextStyle = TextStyle(),
)

val LocalOneDayOneLineTypography = staticCompositionLocalOf { OneDayOneLineTypography() }

val themeTypoScheme = OneDayOneLineTypography(
    h1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 54.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 54.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
)
