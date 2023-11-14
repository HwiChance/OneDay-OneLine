package com.hwichance.onedayoneline.initializer

import android.app.Application
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

class AppInitializer @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards Initializer>,
) {
    suspend fun init(application: Application) {
        withContext(EmptyCoroutineContext) {
            initializers.map {
                async {
                    it.init(application)
                }
            }.awaitAll()
        }
    }
}
