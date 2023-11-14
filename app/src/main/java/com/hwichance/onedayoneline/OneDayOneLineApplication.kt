package com.hwichance.onedayoneline

import android.app.Application
import com.hwichance.onedayoneline.initializer.AppInitializer
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class OneDayOneLineApplication : Application() {
    @Inject
    lateinit var appInitializer: AppInitializer

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            appInitializer.init(this@OneDayOneLineApplication)
        }
    }
}
