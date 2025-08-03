package com.roycemars.royalgame.core.location

import com.roycemars.royalgame.core.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLastKnownLocation(): Flow<Location>
}