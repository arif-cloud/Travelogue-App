package com.example.travelogueapp.domain.model

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Parcelize
@Serializable
data class TravelGuide(
    @SerializedName("cost")
    val cost: String?,
    @SerializedName("foods")
    val foods: List<Food>?,
    @SerializedName("places")
    val places: List<Place>?,
    @SerializedName("travelBagItems")
    val travelBagItems: List<String>?
): Parcelable

val TravelGuideType = object : NavType<TravelGuide>(
    isNullableAllowed = false
) {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun get(bundle: Bundle, key: String): TravelGuide? {
        return bundle.getParcelable(key, TravelGuide::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: TravelGuide) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): TravelGuide {
        return Json.decodeFromString<TravelGuide>(value)
    }

    override fun serializeAsValue(value: TravelGuide): String {
        return Uri.encode(Json.encodeToString(value))
    }
}