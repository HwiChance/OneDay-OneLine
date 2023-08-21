package com.hwichance.design.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hwichance.design.R
import com.hwichance.util.enums.LoginType

@Composable
fun LoginButton(
    type: LoginType,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scaleValue: Float by animateFloatAsState(
        targetValue = if (isPressed) 1.05f else 1f,
        label = "LoginButtonAnimation",
    )

    val buttonImage = when (type) {
        LoginType.KAKAO -> painterResource(id = R.drawable.kakao_login_button)
        LoginType.NAVER -> painterResource(id = R.drawable.naver_login_button)
        LoginType.GOOGLE -> painterResource(id = R.drawable.google_login_button)
    }

    val contentDescription = when (type) {
        LoginType.KAKAO -> stringResource(id = R.string.kakao_login_description)
        LoginType.NAVER -> stringResource(id = R.string.naver_login_description)
        LoginType.GOOGLE -> stringResource(id = R.string.google_login_description)
    }

    Image(
        modifier = Modifier
            .width(200.dp)
            .scale(scaleValue)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        painter = buttonImage,
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
    )
}
