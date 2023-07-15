package com.pd.xchange.network.di

import com.pd.xchange.network.creator.ApiServiceCreator
import com.pd.xchange.network.creator.RetrofitServiceCreator
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    @Binds
    @Singleton
    internal abstract fun bindServiceCreator(retrofitServiceCreator: RetrofitServiceCreator): ApiServiceCreator

    companion object {
        @Provides
        @Singleton
        fun provideMoshi(): Moshi = Moshi.Builder().build()
    }
}