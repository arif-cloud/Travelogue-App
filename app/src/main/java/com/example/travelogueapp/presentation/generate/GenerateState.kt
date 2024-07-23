package com.example.travelogueapp.presentation.generate

import com.example.travelogueapp.domain.model.TravelGuide

data class GenerateState(
    val screenState: ScreenState = ScreenState.INITIAL,
    val data: TravelGuide? = null,
    val error: String = ""
)

enum class ScreenState {
    INITIAL,
    LOADING,
    SUCCESS,
    ERROR
}
