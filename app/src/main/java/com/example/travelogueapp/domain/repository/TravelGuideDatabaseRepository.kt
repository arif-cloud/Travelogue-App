package com.example.travelogueapp.domain.repository

import com.example.travelogueapp.data.local.model.TravelGuideEntity
import kotlinx.coroutines.flow.Flow

interface TravelGuideDatabaseRepository {

    fun getSavedTravelGuides(): Flow<List<TravelGuideEntity>>

    suspend fun saveTravelGuide(travelGuideEntity: TravelGuideEntity)

    suspend fun removeTravelGuide(travelGuideEntity: TravelGuideEntity)

}