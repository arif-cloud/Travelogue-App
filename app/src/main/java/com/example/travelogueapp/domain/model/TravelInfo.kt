package com.example.travelogueapp.domain.model

import com.example.travelogueapp.presentation.generate.component.FeatureInfo

data class TravelInfo(
    val destination: String,
    val travelDate: String,
    val travelDuration: Int,
    val selectedFeatures: List<FeatureInfo>
)
