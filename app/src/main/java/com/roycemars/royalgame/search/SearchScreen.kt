package com.roycemars.royalgame.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Simple search screen showing a text field, list, and map.
 */
@Composable
fun SearchScreen(city: String) {
    val viewModel: SearchViewModel = mavericksViewModel()
    val state by viewModel.collectAsState()
    val query = remember { mutableStateOf("") }

    LaunchedEffect(city) { viewModel.loadSuggestions(city) }

    Column(modifier = Modifier.fillMaxSize()) {
        BasicTextField(value = query.value, onValueChange = {
            query.value = it
            viewModel.search(it, city)
        })

        val suggestions = state.suggestions
        if (suggestions.isNotEmpty()) {
            Text("Suggestions: " + suggestions.joinToString())
        }

        val pagingItems = state.results.collectAsLazyPagingItems()
        androidx.compose.foundation.lazy.LazyColumn {
            items(pagingItems) { place ->
                if (place != null) Text(place.name)
            }
        }

        if (state.isLoading) CircularProgressIndicator()

        val camera = rememberCameraPositionState()
        GoogleMap(cameraPositionState = camera) {
            pagingItems.itemSnapshotList.items.forEach { place ->
                Marker(position = com.google.android.gms.maps.model.LatLng(place.latitude, place.longitude))
            }
        }
    }
}
