package com.example.travelogueapp.presentation.travel_guide.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.travelogueapp.domain.model.Place
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun PlaceItem(
    modifier: Modifier = Modifier,
    place: Place,
    onClickLocationButton: (String) -> Unit,
) {
    Card(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = place.imageUrl,
            contentDescription = "place_image",
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = place.placeName ?: "",
                    style = Typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                IconButton(onClick = { onClickLocationButton(place.placeName.orEmpty()) }) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "location_icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Text(
                text = place.description ?: "",
                style = Typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
private fun PrevPlaceItem() {
    val place = Place(
        placeName = "London",
        description = "The historical place of the world",
        imageUrl = ""
    )
    PlaceItem(place = place, onClickLocationButton = {})
}