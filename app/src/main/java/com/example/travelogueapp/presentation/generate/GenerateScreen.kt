package com.example.travelogueapp.presentation.generate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.travelogueapp.R
import com.example.travelogueapp.domain.model.TravelInfo
import com.example.travelogueapp.presentation.generate.component.FeatureInfo
import com.example.travelogueapp.presentation.generate.component.FeaturesSection
import com.example.travelogueapp.presentation.generate.component.TravelDatePicker
import com.example.travelogueapp.presentation.generate.component.loading.LoadingScreen
import com.example.travelogueapp.presentation.root.Screen
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun GenerateScreen(
    navController: NavController,
    viewModel: GenerateViewModel = hiltViewModel()
) {
    val state by viewModel.generateState.collectAsState()
    var destination by remember { mutableStateOf("") }
    var travelDate by remember { mutableStateOf("") }
    var travelDuration by remember { mutableIntStateOf(0) }
    val selectedFeatures = remember { mutableStateListOf<FeatureInfo>() }
    var showDatePicker by remember { mutableStateOf(false) }
    when (state.screenState) {
        ScreenState.INITIAL -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = stringResource(R.string.greeting), style = Typography.titleLarge)
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = destination,
                    onValueChange = { destination = it },
                    label = { Text("Destination") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = travelDate,
                    onValueChange = { travelDate = it },
                    label = { Text("Travel Date") },
                    placeholder = { Text(text = "MM/DD/YYYY") },
                    trailingIcon = {
                        IconButton(
                            onClick = { showDatePicker = true },
                            content = {
                                Icon(
                                    imageVector = Icons.Outlined.DateRange,
                                    contentDescription = "calendar_icon"
                                )
                            }
                        )
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = travelDuration.toString(),
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Travel Duration") },
                    placeholder = { Text(text = "Enter number of days") },
                    trailingIcon = {
                        Column {
                            Icon(
                                modifier = Modifier.clickable { travelDuration++ },
                                imageVector = Icons.Sharp.KeyboardArrowUp,
                                contentDescription = "add_icon"
                            )
                            Icon(
                                modifier = Modifier.clickable { travelDuration-- },
                                imageVector = Icons.Sharp.KeyboardArrowDown,
                                contentDescription = "minus_icon"
                            )
                        }
                    }
                )
                FeaturesSection(
                    modifier = Modifier.fillMaxWidth(),
                    selectedFeatures = selectedFeatures
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.generateTravelGuide(TravelInfo(destination, travelDate, travelDuration, selectedFeatures.toList())) },
                    content = { Text(text = "Generate", style = Typography.titleMedium) }
                )
            }
        }

        ScreenState.LOADING -> {
            LoadingScreen(
                modifier = Modifier.fillMaxSize(),
                generatedFeatures = selectedFeatures.toList().map { it.title }
            )
        }

        ScreenState.ERROR -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.error, color = Color.Red)
            }
        }

        ScreenState.SUCCESS -> {
            LaunchedEffect(key1 = state.data) {
                state.data?.let {
                    navController.navigate(Screen.Guide(it, false))
                }
            }
        }
    }
    if (showDatePicker) {
        TravelDatePicker(
            onConfirm = { selectedDate -> travelDate = selectedDate },
            onDismiss = { showDatePicker = false }
        )
    }
}