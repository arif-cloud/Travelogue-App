package com.example.travelogueapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("total")
    val total: Int?,
    @SerializedName("totalHits")
    val totalHits: Int?,
    @SerializedName("hits")
    val hits: List<Hit>
)