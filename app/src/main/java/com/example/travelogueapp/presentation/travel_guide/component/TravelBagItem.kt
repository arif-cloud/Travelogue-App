package com.example.travelogueapp.presentation.travel_guide.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun TravelBagItem(
    modifier: Modifier = Modifier,
    item: String
) {
    Card(modifier = modifier) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = item,
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun PrevTravelBagItem() {
    TravelBagItem(item = "Clothes")
}