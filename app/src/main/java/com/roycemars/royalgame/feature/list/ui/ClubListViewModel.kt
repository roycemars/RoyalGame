package com.roycemars.royalgame.feature.list.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.roycemars.royalgame.core.location.LocationRepository
import com.roycemars.royalgame.core.model.Location
import com.roycemars.royalgame.feature.list.state.ClubListState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ClubListViewModel @AssistedInject constructor(
    @Assisted state: ClubListState,
//    private val repository: BusinessRepository
): MavericksViewModel<ClubListState>(state) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ClubListViewModel, ClubListState> {
        override fun create(state: ClubListState): ClubListViewModel
    }

    companion object :
        MavericksViewModelFactory<ClubListViewModel, ClubListState> by hiltMavericksViewModelFactory()
}