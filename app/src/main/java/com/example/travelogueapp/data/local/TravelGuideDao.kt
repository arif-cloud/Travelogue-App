package com.example.travelogueapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.travelogueapp.data.local.model.TravelGuideEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TravelGuideDao {

    @Query("SELECT * FROM saved_travel_guides")
    fun getAllTravelGuides(): Flow<List<TravelGuideEntity>>

    @Insert
    suspend fun insertTravelGuide(travelGuideEntity: TravelGuideEntity)

    @Delete
    suspend fun deleteTravelGuide(travelGuideEntity: TravelGuideEntity)

}