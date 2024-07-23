package com.example.travelogueapp.presentation.saved

import com.example.travelogueapp.data.local.model.TravelGuideEntity

data class SavedGuideState(
    val isLoading: Boolean = false,
    val data: List<TravelGuideEntity>? = null,
    val error: String = ""
)
