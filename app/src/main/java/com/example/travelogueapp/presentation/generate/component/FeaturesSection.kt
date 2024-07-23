package com.example.travelogueapp.presentation.generate.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.travelogueapp.R

@Composable
fun FeaturesSection(
    modifier: Modifier = Modifier,
    selectedFeatures: SnapshotStateList<FeatureInfo>
) {
    val features = listOf(
        FeatureInfo(
            stringResource(id = R.string.places_feature_title),
            "Discover the most interesting places."
        ),
        FeatureInfo(
            stringResource(id = R.string.bag_feature_title),
            "Essential items for your travel bag."
        ),
        FeatureInfo(
            stringResource(id = R.string.foods_feature_title),
            "Enjoy the flavors of your destination."
        ),
        FeatureInfo(
            stringResource(id = R.string.cost_feature_title),
            "Get an idea of expected expenses for your trip."
        )
    )
    features.forEach { featureInfo ->
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = selectedFeatures.contains(featureInfo),
                onCheckedChange = { isChecked -> if (isChecked) selectedFeatures.add(featureInfo) else selectedFeatures.remove(featureInfo) }
            )
            Column {
                Text(text = featureInfo.title, style = MaterialTheme.typography.titleSmall)
                Text(text = featureInfo.description, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}

data class FeatureInfo(
    val title: String,
    val description: String,
)