package com.example.travelogueapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.travelogueapp.domain.model.Food
import com.example.travelogueapp.domain.model.Place

@Entity(tableName = "saved_travel_guides")
data class TravelGuideEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("label")
    val label: String,
    @ColumnInfo("cost")
    val cost: String?,
    @ColumnInfo("foods")
    val foods: List<Food>?,
    @ColumnInfo("places")
    val places: List<Place>?,
    @ColumnInfo("travelBagItems")
    val travelBagItems: List<String>?
)
