package com.example.travelogueapp.data.remote

import com.example.travelogueapp.common.Constants
import com.example.travelogueapp.data.remote.dto.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET(".")
    suspend fun generateImages(@Query("q") q: String, @Query("key") key: String = Constants.PHOTO_API_KEY): PhotoResponse

}