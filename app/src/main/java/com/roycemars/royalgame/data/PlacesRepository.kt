package com.roycemars.royalgame.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class PlacesRepository(
    private val api: PlacesApi,
    private val dao: PlaceDao
) {
    fun search(query: String, city: String): Flow<PagingData<Place>> =
        Pager(PagingConfig(pageSize = 20)) {
            dao.searchPaging(query)
        }.flow

    suspend fun suggestions(city: String): List<String> = dao.suggestions(city)

    suspend fun cache(places: List<Place>) {
        dao.insertAll(places)
    }
}
