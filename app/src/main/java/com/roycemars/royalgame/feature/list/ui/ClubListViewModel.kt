package com.roycemars.royalgame.feature.list.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.airbnb.mvrx.MavericksState
import com.roycemars.royalgame.core.location.LocationRepository
import com.roycemars.royalgame.core.model.Location

data class MainState(
    val location: Location
) : MavericksState

class MainViewModel
constructor(private val locationRepository: LocationRepository) : ViewModel()
{
    fun observeLocation(context: Context) {
    }
}