package com.hwichance.onedayoneline.initializer

import android.app.Application

interface Initializer {
    suspend fun init(application: Application)
}
