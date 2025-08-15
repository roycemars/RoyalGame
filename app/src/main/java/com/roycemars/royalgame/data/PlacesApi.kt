package com.roycemars.royalgame.data

import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApi {
    @GET("places")
    suspend fun search(
        @Query("q") query: String,
        @Query("city") city: String
    ): List<Place>
}
