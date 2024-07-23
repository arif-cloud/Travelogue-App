package com.example.travelogueapp.presentation.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelogueapp.data.mapper.toTravelGuide
import com.example.travelogueapp.presentation.root.Screen
import com.example.travelogueapp.presentation.saved.component.EmptyListWarning
import com.example.travelogueapp.presentation.saved.component.SavedGuideItem
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun SavedGuideScreen(
    navController: NavController,
    viewModel: SavedGuideViewModel = hiltViewModel()
) {
    val state by viewModel.savedGuideState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        state.data?.let { travelGuideList ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(travelGuideList) {travelGuideEntity ->
                    SavedGuideItem(
                        modifier = Modifier.fillMaxWidth(),
                        label = travelGuideEntity.label,
                        onClickItem = { navController.navigate(Screen.Guide(travelGuideEntity.toTravelGuide(), true)) },
                        onClickDeleteButton = { viewModel.removeTravelGuide(travelGuideEntity) }
                    )
                }
            }
            if (travelGuideList.isEmpty()) {
                EmptyListWarning(modifier = Modifier.align(Alignment.Center))
            }
        }
        if (state.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        if (state.error.isNotEmpty())
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = state.error,
                color = Color.Red
            )
    }
}