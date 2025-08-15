package com.roycemars.royalgame

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.navigation.DefaultNavigationViewModelDelegateFactory
import dagger.hilt.android.HiltAndroidApp
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.roycemars.royalgame.data.PrefetchWorker
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class RoyalGameApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)

        // Schedule periodic prefetch of places for offline support
        val work = PeriodicWorkRequestBuilder<PrefetchWorker>(12, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "prefetch", ExistingPeriodicWorkPolicy.UPDATE, work
        )
    }
}