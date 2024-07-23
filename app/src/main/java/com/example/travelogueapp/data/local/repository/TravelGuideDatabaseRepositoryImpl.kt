package com.example.travelogueapp.data.local.repository

import com.example.travelogueapp.data.local.TravelGuideDao
import com.example.travelogueapp.data.local.model.TravelGuideEntity
import com.example.travelogueapp.domain.repository.TravelGuideDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TravelGuideDatabaseRepositoryImpl @Inject constructor(
    private val dao: TravelGuideDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): TravelGuideDatabaseRepository {

    override fun getSavedTravelGuides(): Flow<List<TravelGuideEntity>> {
        return dao.getAllTravelGuides()
    }

    override suspend fun saveTravelGuide(travelGuideEntity: TravelGuideEntity) = withContext(ioDispatcher) {
        dao.insertTravelGuide(travelGuideEntity)
    }

    override suspend fun removeTravelGuide(travelGuideEntity: TravelGuideEntity) = withContext(ioDispatcher) {
        dao.deleteTravelGuide(travelGuideEntity)
    }
}