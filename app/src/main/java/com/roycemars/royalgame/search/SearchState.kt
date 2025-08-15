package com.roycemars.royalgame.search

import androidx.paging.PagingData
import com.airbnb.mvrx.MavericksState
import com.roycemars.royalgame.data.Place

/**
 * State for [SearchViewModel].
 */
data class SearchState(
    val query: String = "",
    val city: String = "",
    val results: PagingData<Place> = PagingData.empty(),
    val suggestions: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) : MavericksState
