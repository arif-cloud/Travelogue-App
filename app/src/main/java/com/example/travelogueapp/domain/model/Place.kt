package com.example.travelogueapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Place(
    @SerializedName("description")
    val description: String?,
    @SerializedName("placeName")
    val placeName: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?
): Parcelable