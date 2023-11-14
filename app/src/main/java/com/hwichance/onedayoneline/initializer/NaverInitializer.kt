package com.hwichance.onedayoneline.initializer

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import com.hwichance.feature.login.helper.NaverLoginHelper
import com.navercorp.nid.oauth.activity.NidOAuthCustomTabActivity
import javax.inject.Inject

class NaverInitializer @Inject constructor() : Initializer {
    override suspend fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity is NidOAuthCustomTabActivity) {
                    NaverLoginHelper.initializeSdk(activity)
                }
                application.unregisterActivityLifecycleCallbacks(this)
            }

            override fun onActivityStarted(activity: Activity) = Unit

            override fun onActivityResumed(activity: Activity) = Unit

            override fun onActivityPaused(activity: Activity) = Unit

            override fun onActivityStopped(activity: Activity) = Unit

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

            override fun onActivityDestroyed(activity: Activity) = Unit
        })
    }
}
