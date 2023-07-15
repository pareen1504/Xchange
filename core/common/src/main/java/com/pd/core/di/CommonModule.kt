package com.pd.core.di

import com.pd.core.network.tracker.ConnectivityManagerNetworkMonitor
import com.pd.core.network.tracker.NetworkMonitor
import com.pd.core.prefence.AppPreference
import com.pd.core.prefence.AppPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {
    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor

    @Binds
    fun bindsAppPreference(
        appPreferenceImpl: AppPreferenceImpl
    ): AppPreference
}
