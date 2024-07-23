package com.example.travelogueapp.data.local.converter

import androidx.room.TypeConverter
import com.example.travelogueapp.domain.model.Food
import com.example.travelogueapp.domain.model.Place
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromPlaceList(value: List<Place>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Place>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toPlaceList(value: String?): List<Place>? {
        if (value == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<Place>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromFoodList(value: List<Food>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Food>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toFoodList(value: String?): List<Food>? {
        if (value == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<Food>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTravelBagItemsList(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toTravelBagItemsList(value: String?): List<String>? {
        if (value == null) return null
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}