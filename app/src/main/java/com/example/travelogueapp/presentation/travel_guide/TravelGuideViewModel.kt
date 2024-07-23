package com.example.travelogueapp.presentation.travel_guide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelogueapp.data.mapper.toTravelGuideEntity
import com.example.travelogueapp.domain.model.TravelGuide
import com.example.travelogueapp.domain.repository.TravelGuideDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TravelGuideViewModel @Inject constructor(
    private val travelGuideDatabaseRepository: TravelGuideDatabaseRepository
): ViewModel() {

    fun saveTravelGuide(label: String, travelGuide: TravelGuide, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        try {
            travelGuideDatabaseRepository.saveTravelGuide(travelGuide.toTravelGuideEntity(label))
            onResult(true)
        } catch (e: Exception) {
            onResult(false)
        }
    }

}