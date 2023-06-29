package com.hwichance.design.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class OneDayOneLineShape(
    val radius4: CornerBasedShape = CircleShape,
    val radius8: CornerBasedShape = CircleShape,
    val radius12: CornerBasedShape = CircleShape,
    val radius16: CornerBasedShape = CircleShape,
    val radiusHalf: CornerBasedShape = CircleShape,
)

val LocalOneDayOneLineShape = staticCompositionLocalOf { OneDayOneLineShape() }

val themeShapeScheme = OneDayOneLineShape(
    radius4 = RoundedCornerShape(size = 4.dp),
    radius8 = RoundedCornerShape(size = 8.dp),
    radius12 = RoundedCornerShape(size = 12.dp),
    radius16 = RoundedCornerShape(size = 16.dp),
    radiusHalf = RoundedCornerShape(percent = 50),
)
