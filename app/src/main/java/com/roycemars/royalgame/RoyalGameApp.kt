package com.roycemars.royalgame

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RoyalGameApp: Application() {
    override fun onCreate() {
        super.onCreate()

        Mavericks.initialize(this)

        val apiKey = BuildConfig.MAPS_API_KEY
        if (!Places.isInitialized()) {
            Places.initializeWithNewPlacesApiEnabled(applicationContext, apiKey)
        }
    }
}