package com.hwichance.onedayoneline.start

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hwichance.design.theme.OneDayOneLineTheme
import com.hwichance.onedayoneline.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartActivity @Inject constructor() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneDayOneLineTheme {
                StartNavHost(
                    navigateToHome = {
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    },
                )
            }
        }
    }
}
