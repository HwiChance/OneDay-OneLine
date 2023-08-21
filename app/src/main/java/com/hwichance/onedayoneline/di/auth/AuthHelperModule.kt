package com.hwichance.onedayoneline.di.auth

import com.hwichance.feature.login.helper.GoogleLoginHelper
import com.hwichance.feature.login.helper.LoginHelper
import com.hwichance.feature.login.helper.LoginManager
import com.hwichance.feature.login.helper.LoginManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthHelperModule {
    @Binds
    abstract fun provideGoogleLoginHelper(googleLoginHelper: GoogleLoginHelper): LoginHelper

    @Binds
    abstract fun provideLoginManager(loginManagerImpl: LoginManagerImpl): LoginManager
}
