package com.hwichance.design.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val Black10 = Color(0xfff7f6f3)
private val Black30 = Color(0xffe5e3e1)
private val Black50 = Color(0xffa3a2a0)
private val Black70 = Color(0xff666562)
private val Black90 = Color(0xff252422)

private val Brown10 = Color(0xffccc5b9)
private val Brown30 = Color(0xff867a63)
private val Brown50 = Color(0xff544526)
private val Brown70 = Color(0xff3e3119)
private val Brown90 = Color(0xff261800)

private val Orange10 = Color(0xfff9eae8)
private val Orange30 = Color(0xfff9af95)
private val Orange50 = Color(0xfff77a4a)
private val Orange70 = Color(0xffeb5e28)
private val Orange90 = Color(0xffcf5221)

private val Green10 = Color(0xffefefef)
private val Green30 = Color(0xffb4bebc)
private val Green50 = Color(0xff7b918e)
private val Green70 = Color(0xff586f6b)
private val Green90 = Color(0xff3a4746)

private val Red10 = Color(0xfff7d2dc)
private val Red30 = Color(0xffdb7f8e)
private val Red50 = Color(0xfff24f62)
private val Red70 = Color(0xffd04056)
private val Red90 = Color(0xffb62f43)

private val Blue10 = Color(0xffe1e8f4)
private val Blue30 = Color(0xff80a1d4)
private val Blue50 = Color(0xff0063b8)
private val Blue70 = Color(0xff0043a3)
private val Blue90 = Color(0xff00318d)

val lightThemeColorScheme = OneDayOneLineColor(
    bgPrimary = Black10,
    fillPrimary = Green50,
    fillSecondary = Orange50,
    fgPrimary = Black90,
    linePrimary = Black90,
)

val darkThemeColorScheme = OneDayOneLineColor(
    bgPrimary = Black90,
    fillPrimary = Green50,
    fillSecondary = Orange50,
    fgPrimary = Black10,
    linePrimary = Black10,
)

val materialLightColorScheme = lightColors(
    primary = lightThemeColorScheme.fillPrimary,
    secondary = lightThemeColorScheme.fillSecondary,
)

val materialDarkColorScheme = lightColors(
    primary = darkThemeColorScheme.fillPrimary,
    secondary = darkThemeColorScheme.fillSecondary,
)
