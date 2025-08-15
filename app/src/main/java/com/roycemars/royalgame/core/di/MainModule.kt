package com.roycemars.royalgame.core.di

import android.content.Context
import androidx.room.Room
import com.roycemars.royalgame.core.location.LocationRepository
import com.roycemars.royalgame.core.location.LocationRepositoryImpl
import com.roycemars.royalgame.data.AppDatabase
import com.roycemars.royalgame.data.PlaceDao
import com.roycemars.royalgame.data.PlacesApi
import com.roycemars.royalgame.data.PlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun provideLocationRepo(@ApplicationContext context: Context): LocationRepository =
        LocationRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "places.db").build()

    @Provides
    fun provideDao(db: AppDatabase): PlaceDao = db.placeDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://example.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit): PlacesApi = retrofit.create(PlacesApi::class.java)

    @Provides
    fun providePlacesRepository(api: PlacesApi, dao: PlaceDao): PlacesRepository =
        PlacesRepository(api, dao)
}
