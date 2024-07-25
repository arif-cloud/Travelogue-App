package com.example.travelogueapp.presentation.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelogueapp.domain.model.TravelGuide
import com.example.travelogueapp.domain.model.TravelInfo
import com.example.travelogueapp.domain.repository.TravelGuideRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateViewModel @Inject constructor(
    private val travelGuideRepository: TravelGuideRepository,
) : ViewModel() {

    private val _generateState = MutableStateFlow(GenerateState())
    val generateState: StateFlow<GenerateState> = _generateState.asStateFlow()

    fun generateTravelGuide(travelInfo: TravelInfo) = viewModelScope.launch {
        try {
            _generateState.value = GenerateState(screenState = ScreenState.LOADING)
            val prompt = createPrompt(travelInfo)
            val travelResponse = travelGuideRepository.generateTravelResponse(prompt)
            val updatedTravelResponse = updateTravelResponseWithImages(travelResponse)
            _generateState.value = GenerateState(screenState = ScreenState.SUCCESS, data = updatedTravelResponse)
        } catch (e: Exception) {
            _generateState.value =
                GenerateState(screenState = ScreenState.ERROR, error = e.localizedMessage.orEmpty())
        }
    }

    private suspend fun updateTravelResponseWithImages(travelGuide: TravelGuide): TravelGuide {
        val updatedPlaces = travelGuide.places?.map { place ->
            val imageUrl = travelGuideRepository.generateImageUrl(place.placeName.orEmpty())
            place.copy(imageUrl = imageUrl)
        }
        val updatedFoods = travelGuide.foods?.map { food ->
            val imageUrl = travelGuideRepository.generateImageUrl(food.foodName.orEmpty())
            food.copy(imageUrl = imageUrl)
        }
        return travelGuide.copy(places = updatedPlaces, foods = updatedFoods)
    }

    private fun createPrompt(travelInfo: TravelInfo): String {
        val basePrompt = "For a ${travelInfo.travelDuration}-day trip to ${travelInfo.destination} dated ${travelInfo.travelDate}, "
        val featuresPrompt = travelInfo.selectedFeatures.joinToString(separator = ", ") { feature ->
            when (feature.title) {
                "Places to Visit" -> "list the 5 places to visit"
                "Travel Bag Creator" -> "list the important items to have in your travel bag"
                "Must-Try Foods" -> "list the 3 most famous local dishes"
                "Estimated Travel Costs" -> "give the estimated cost of this trip in dollars"
                else -> ""
            }
        }
        val (jsonSchemas, returnFields) = travelInfo.selectedFeatures.map { feature ->
            when (feature.title) {
                "Places to Visit" -> Pair(
                    "Place = {\"placeName\": str, \"description\": str}",
                    "\"places\": list[Place]"
                )
                "Must-Try Foods" -> Pair(
                    "Food = {\"foodName\": str, \"ingredients\": list[str]}",
                    "\"foods\": list[Food]"
                )
                "Travel Bag Creator" -> Pair(null, "\"travelBagItems\": list[str]")
                "Estimated Travel Costs" -> Pair(null, "\"cost\": str")
                else -> Pair(null, null)
            }
        }.unzip()
        val jsonSchemaPrompt = """
        Using this JSON schema: 
        ${jsonSchemas.filterNotNull().joinToString(separator = " ")} 
        Return a {${returnFields.filterNotNull().joinToString(separator = ", ")}}""".trimIndent()
        return "$basePrompt $featuresPrompt. $jsonSchemaPrompt"
    }

    fun resetState() {
        _generateState.value = GenerateState()
    }

}