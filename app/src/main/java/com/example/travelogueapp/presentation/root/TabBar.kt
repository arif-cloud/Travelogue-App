package com.example.travelogueapp.presentation.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun TabBar(
    navController: NavController
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("Generate", "Saved")
    TabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = title,
                        style = Typography.titleMedium
                    )
                },
                selected = selectedTabIndex == index,
                onClick = {
                    if (selectedTabIndex != index) {
                        selectedTabIndex = index
                    }
                    when (selectedTabIndex) {
                        0 -> navController.navigate(Screen.Generate)
                        1 -> navController.navigate(Screen.Saved)
                    }
                }
            )
        }
    }
}