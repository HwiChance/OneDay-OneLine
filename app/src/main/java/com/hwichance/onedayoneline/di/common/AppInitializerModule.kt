package com.hwichance.onedayoneline.di.common

import com.hwichance.onedayoneline.initializer.Initializer
import com.hwichance.onedayoneline.initializer.NaverInitializer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class AppInitializerModule {
    @Binds
    @IntoSet
    abstract fun provideNaverInitializer(initializer: NaverInitializer): Initializer
}
