package com.roycemars.royalgame.feature.list.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest

@Composable
fun CustomPlaceSearchComponent(
    onPlaceSelected: (Place) -> Unit
) {
    val context = LocalContext.current
    val placesClient = remember { Places.createClient(context) }
    var searchQuery by remember { mutableStateOf("") }
    var predictions by remember { mutableStateOf<List<AutocompletePrediction>>(emptyList()) }
    val token = remember { AutocompleteSessionToken.newInstance() }
    val coroutineScope = rememberCoroutineScope()

    Column {
        TextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                if (query.isNotBlank()) {
                    val request = FindAutocompletePredictionsRequest.builder()
                        .setSessionToken(token)
                        .setQuery(query)
                         .setCountries("US") // Optional: Bias to specific countries
                        // .setTypesFilter(listOf(PlaceTypes.ESTABLISHMENT)) // Optional
                        .build()

                    placesClient.findAutocompletePredictions(request)
                        .addOnSuccessListener { response ->
                            predictions = response.autocompletePredictions
                        }
                        .addOnFailureListener { exception ->
                            Log.e("PlacesSearch", "Prediction error", exception)
                            predictions = emptyList()
                        }
                } else {
                    predictions = emptyList()
                }
            },
            label = { Text("Search for a place") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn {
            items(predictions) { prediction ->
                Text(
                    text = prediction.getFullText(null).toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Fetch place details when a prediction is clicked
                            val placeFields = listOf(
                                Place.Field.ID,
                                Place.Field.NAME,
                                Place.Field.LAT_LNG,
                                Place.Field.ADDRESS
                            )
                            val fetchPlaceRequest = FetchPlaceRequest.builder(prediction.placeId, placeFields)
                                .setSessionToken(token) // Use the same token
                                .build()

                            placesClient.fetchPlace(fetchPlaceRequest)
                                .addOnSuccessListener { response ->
                                    val place = response.place
                                    Log.i("PlacesSearch", "Selected Place: ${place.name}")
                                    onPlaceSelected(place) // Callback with the selected Place object
                                    searchQuery = place.name ?: "" // Update TextField with selected name
                                    predictions = emptyList() // Clear predictions
                                }
                                .addOnFailureListener { exception ->
                                    Log.e("PlacesSearch", "Fetch place error", exception)
                                }
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}

// Example usage in your GameMapScreen or another screen
@Composable
fun ListScreen() {
    // ...
    CustomPlaceSearchComponent { place ->
        // Handle the selected place, e.g., move map camera to place.latLng
        // cameraPositionState.animate(...)
        Log.d("SelectedPlace", "Name: ${place.name}, LatLng: ${place.latLng}")
    }
    // ...
}

