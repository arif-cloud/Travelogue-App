package com.example.travelogueapp.presentation.root

import com.example.travelogueapp.domain.model.TravelGuide
import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    data object Generate: Screen()

    @Serializable
    data object Saved: Screen()

    @Serializable
    data class Guide(
        val travelGuide: TravelGuide,
        val isSaved: Boolean
    ): Screen()

}