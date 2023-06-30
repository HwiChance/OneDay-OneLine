package com.hwichance.onedayoneline.start

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartApp()
        }
    }
}
