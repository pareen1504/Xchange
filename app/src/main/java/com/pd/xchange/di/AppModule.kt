package com.pd.xchange.di

import com.pd.xchange.config.AppConfigImpl
import com.pd.xchange.jvm.config.AppConfig
import com.pd.xchange.jvm.di.DispatchersModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DispatchersModule::class])
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindsAppConfig(
        appConfigImpl: AppConfigImpl
    ): AppConfig
}