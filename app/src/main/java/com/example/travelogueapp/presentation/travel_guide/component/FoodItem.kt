package com.example.travelogueapp.presentation.travel_guide.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.travelogueapp.domain.model.Food
import com.example.travelogueapp.ui.theme.Typography

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FoodItem(
    modifier: Modifier = Modifier,
    food: Food,
) {
    Card(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = food.imageUrl,
            contentDescription = "food_image",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = food.foodName ?: "",
                style = Typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                food.ingredients.forEach { ingredientItem ->
                    OutlinedCard {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = ingredientItem ?: "",
                            style = Typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PrevFoodItem() {
    val food = Food(
        foodName = "Pizza",
        ingredients = listOf("Tomato", "Cheese"),
        imageUrl = ""
    )
    FoodItem(food = food)
}