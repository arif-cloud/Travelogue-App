package com.example.travelogueapp.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelogueapp.data.local.model.TravelGuideEntity
import com.example.travelogueapp.domain.repository.TravelGuideDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedGuideViewModel @Inject constructor(
    private val travelGuideDatabaseRepository: TravelGuideDatabaseRepository
): ViewModel() {

    private val _savedGuideState = MutableStateFlow(SavedGuideState())
    val savedGuideState: StateFlow<SavedGuideState> = _savedGuideState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        try {
            _savedGuideState.value = SavedGuideState(isLoading = true)
            travelGuideDatabaseRepository.getSavedTravelGuides().flowOn(Dispatchers.IO).collect { savedTravelGuides ->
                _savedGuideState.value = SavedGuideState(data = savedTravelGuides)
            }
        } catch (e: Exception) {
            _savedGuideState.value = SavedGuideState(error = e.localizedMessage.orEmpty())
        }
    }

    fun removeTravelGuide(travelGuideEntity: TravelGuideEntity) = viewModelScope.launch {
        travelGuideDatabaseRepository.removeTravelGuide(travelGuideEntity)
    }

}