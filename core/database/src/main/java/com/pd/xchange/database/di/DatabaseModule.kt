package com.pd.xchange.database.di

import android.content.Context
import androidx.room.Room
import com.pd.xchange.database.DbConstants.XCHANGE_DATABASE
import com.pd.xchange.database.XchangeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesXchangeDatabase(
        @ApplicationContext context: Context
    ): XchangeDatabase = Room.databaseBuilder(
        context,
        XchangeDatabase::class.java,
        XCHANGE_DATABASE
    ).build()

}