package com.example.travelogueapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.travelogueapp.data.local.converter.Converters
import com.example.travelogueapp.data.local.model.TravelGuideEntity

@Database(entities = [TravelGuideEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class TravelGuideDatabase : RoomDatabase() {
    abstract fun travelGuideDao(): TravelGuideDao
}