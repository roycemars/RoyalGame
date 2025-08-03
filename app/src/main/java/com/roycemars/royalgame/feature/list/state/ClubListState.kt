package com.roycemars.royalgame.feature.list.state

import com.airbnb.mvrx.MavericksState
import com.roycemars.royalgame.core.model.Club

class ClubListState (
    val businesses: List<Club> = emptyList(),
    val filteredList: List<Club> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) : MavericksState