package com.example.travelogueapp.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Food(
    @SerializedName("foodName")
    val foodName: String?,
    @SerializedName("ingredients")
    val ingredients: List<String?>,
    @SerializedName("imageUrl")
    val imageUrl: String?
): Parcelable