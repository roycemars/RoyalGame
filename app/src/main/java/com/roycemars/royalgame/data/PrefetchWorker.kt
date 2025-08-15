package com.roycemars.royalgame.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

/**
 * Periodically prefetches nearby places for offline search.
 */
class PrefetchWorker(
    context: Context,
    params: WorkerParameters,
    private val api: PlacesApi,
    private val repo: PlacesRepository
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            val city = inputData.getString("city") ?: return Result.success()
            val places = api.search("", city)
            repo.cache(places)
            Result.success()
        } catch (t: Throwable) {
            Result.retry()
        }
    }
}
