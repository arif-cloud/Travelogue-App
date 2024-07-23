package com.example.travelogueapp.data.mapper

import com.example.travelogueapp.data.local.model.TravelGuideEntity
import com.example.travelogueapp.domain.model.TravelGuide

fun TravelGuide.toTravelGuideEntity(label: String): TravelGuideEntity {
    return TravelGuideEntity(
        label = label,
        places = places,
        travelBagItems = travelBagItems,
        foods = foods,
        cost = cost
    )
}

fun TravelGuideEntity.toTravelGuide(): TravelGuide {
    return TravelGuide(
        places = places,
        travelBagItems = travelBagItems,
        foods = foods,
        cost = cost
    )
}