package com.example.travelogueapp.presentation.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.travelogueapp.domain.model.TravelGuide
import com.example.travelogueapp.domain.model.TravelGuideType
import com.example.travelogueapp.presentation.generate.GenerateScreen
import com.example.travelogueapp.presentation.saved.SavedGuideScreen
import com.example.travelogueapp.presentation.travel_guide.TravelGuideScreen
import kotlin.reflect.typeOf

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Generate
    ) {
        composable<Screen.Generate> {
            GenerateScreen(navController)
        }
        composable<Screen.Saved> {
            SavedGuideScreen(navController)
        }
        composable<Screen.Guide>(
            typeMap = mapOf(typeOf<TravelGuide>() to TravelGuideType)
        ) { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.Guide>()
            TravelGuideScreen(travelGuide = args.travelGuide, isSaved = args.isSaved)
        }
    }
}