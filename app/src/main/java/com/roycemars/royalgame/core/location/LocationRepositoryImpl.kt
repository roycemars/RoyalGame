package com.roycemars.royalgame.core.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.roycemars.royalgame.core.model.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LocationRepositoryImpl
@Inject
constructor(
    @ApplicationContext
    private val context: Context
) : LocationRepository {
    override fun getLastKnownLocation(): Flow<Location> = callbackFlow {
        val client = LocationServices.getFusedLocationProviderClient(context)

        val permissionGranted = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!permissionGranted) {
            close(SecurityException("Missing ACCESS_FINE_LOCATION permission"))
            return@callbackFlow
        }

        client.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    trySend(Location(location.latitude, location.longitude))
                } else {
                    close(NullPointerException("Location is null"))
                }
            }
            .addOnFailureListener { exception ->
                close(exception)
            }

        awaitClose { /* no-op */ }
    }
}