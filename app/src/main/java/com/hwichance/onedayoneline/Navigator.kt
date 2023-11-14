package com.hwichance.onedayoneline

import android.content.Context
import com.hwichance.onedayoneline.home.HomeActivity
import com.hwichance.onedayoneline.start.LoginActivity

object Navigator {
    fun toHome(context: Context) {
        val intent = HomeActivity.getCallingIntent(context)
        context.startActivity(intent)
    }

    fun toLogin(context: Context) {
        val intent = LoginActivity.getCallingIntent(context)
        context.startActivity(intent)
    }
}
