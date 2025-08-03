package com.roycemars.royalgame.core.di

import android.content.Context
import com.roycemars.royalgame.core.location.LocationRepository
import com.roycemars.royalgame.core.location.LocationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    @Provides
    fun provideRepository(@ApplicationContext context: Context): LocationRepository =
        LocationRepositoryImpl(context)
}