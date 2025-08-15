package com.roycemars.royalgame.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a bookable location such as a golf club or tennis court.
 */
@Entity(tableName = "places")
data class Place(
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val latitude: Double,
    val longitude: Double,
    val city: String
)
