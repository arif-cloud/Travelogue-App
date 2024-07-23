package com.example.travelogueapp.data.remote.repository

import com.example.travelogueapp.data.remote.PhotoApi
import com.example.travelogueapp.domain.model.TravelGuide
import com.example.travelogueapp.domain.repository.TravelGuideRepository
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TravelGuideRepositoryImpl @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val photoApi: PhotoApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): TravelGuideRepository {

    override suspend fun generateTravelResponse(prompt: String): TravelGuide = withContext(ioDispatcher) {
        val response = generativeModel.generateContent(prompt)
        Gson().fromJson(response.text, TravelGuide::class.java)
    }

    override suspend fun generateImageUrl(q: String): String = withContext(ioDispatcher) {
        val response = photoApi.generateImages(q)
        response.hits.firstOrNull()?.webFormatURL.orEmpty()
    }

}