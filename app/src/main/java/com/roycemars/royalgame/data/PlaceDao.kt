package com.roycemars.royalgame.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaceDao {
    @Query("SELECT * FROM places WHERE name LIKE '%' || :query || '%' ORDER BY name")
    fun searchPaging(query: String): PagingSource<Int, Place>

    @Query("SELECT name FROM places WHERE city = :city ORDER BY name")
    suspend fun suggestions(city: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(places: List<Place>)
}
