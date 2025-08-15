package com.roycemars.royalgame.search

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.airbnb.mvrx.MavericksViewModel
import com.roycemars.royalgame.data.PlacesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo: PlacesRepository,
    application: Application
) : MavericksViewModel<SearchState>(SearchState()) {

    fun search(query: String, city: String) {
        setState { copy(query = query, city = city, isLoading = true) }
        viewModelScope.launch {
            repo.search(query, city)
                .cachedIn(viewModelScope)
                .collect { paging ->
                    setState { copy(results = paging, isLoading = false, error = null) }
                }
        }
    }

    fun loadSuggestions(city: String) {
        viewModelScope.launch {
            val hints = repo.suggestions(city)
            setState { copy(suggestions = hints) }
        }
    }
}
