package com.example.travelogueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.travelogueapp.presentation.root.NavigationGraph
import com.example.travelogueapp.presentation.root.TabBar
import com.example.travelogueapp.ui.theme.TravelogueAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelogueAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TabBar(navController = navController)
                    }
                ) {
                    NavigationGraph(
                        modifier = Modifier.padding(it),
                        navController = navController
                    )
                }
            }
        }
    }
}