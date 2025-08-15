package com.roycemars.royalgame.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Place::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}
