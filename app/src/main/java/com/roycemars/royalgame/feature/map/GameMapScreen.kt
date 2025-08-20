package com.roycemars.royalgame.feature.map

import android.annotation.SuppressLint
import androidx.activity.result.launch
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
fun GameMapScreen() {
    val singapore = LatLng(1.35, 103.87) // Example location
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f) // Zoom level (10-15 is good for city level)
    }
    val coroutineScope = rememberCoroutineScope() // For animating camera

    // Basic map properties and UI settings
    val mapProperties = MapProperties(
        isMyLocationEnabled = false, // Set to true to show user's location (requires permission handling)
        mapType = MapType.NORMAL,
        isTrafficEnabled = false
    )

    val uiSettings = MapUiSettings(
        zoomControlsEnabled = true, // Show +/- zoom buttons
        myLocationButtonEnabled = false, // Show My Location button (requires isMyLocationEnabled = true)
        mapToolbarEnabled = true // Show toolbar when a marker is clicked
    )

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = uiSettings,
        onMapLoaded = {
            // You can perform actions when the map is fully loaded
            // For example, animate camera to a specific location after load
            coroutineScope.launch {
                // cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(anotherLocation, 15f))
            }
        },
        onMapClick = { latLng ->
            // Handle map click events if needed
            println("Map clicked at: ${latLng.latitude}, ${latLng.longitude}")
        }
    ) {
        // You can add Markers, Polylines, Polygons, etc., here
        Marker(
            state = MarkerState(position = singapore),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
        // Add more markers as needed
        Marker(
            state = MarkerState(position = LatLng(1.3584, 103.9895)), // Example Changi Airport
            title = "Changi Airport",
            snippet = "An example marker"
        )
    }
}