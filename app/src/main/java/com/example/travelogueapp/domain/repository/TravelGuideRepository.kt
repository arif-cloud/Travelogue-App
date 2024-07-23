package com.example.travelogueapp.domain.repository

import com.example.travelogueapp.domain.model.TravelGuide

interface TravelGuideRepository {

    suspend fun generateTravelResponse(prompt: String): TravelGuide

    suspend fun generateImageUrl(q: String): String

}