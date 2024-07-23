package com.example.travelogueapp.presentation.travel_guide

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelogueapp.R
import com.example.travelogueapp.domain.model.TravelGuide
import com.example.travelogueapp.presentation.travel_guide.component.FoodItem
import com.example.travelogueapp.presentation.travel_guide.component.PlaceItem
import com.example.travelogueapp.presentation.travel_guide.component.SaveDialog
import com.example.travelogueapp.presentation.travel_guide.component.TravelBagSection
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun TravelGuideScreen(
    travelGuide: TravelGuide,
    isSaved: Boolean,
    viewModel: TravelGuideViewModel = hiltViewModel()
) {
    var openSaveDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        travelGuide.places?.let { placeList ->
            Text(text = stringResource(id = R.string.places_feature_title), style = Typography.titleLarge)
            placeList.forEach { place ->
                PlaceItem(
                    modifier = Modifier.fillMaxWidth(),
                    place = place,
                    onClickLocationButton = { placeName ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$placeName"))
                        context.startActivity(intent)
                    }
                )
            }
        }
        travelGuide.travelBagItems?.let { itemList ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.bag_feature_title), style = Typography.titleLarge)
            TravelBagSection(
                modifier = Modifier.fillMaxWidth(),
                travelBagItems = itemList
            )
        }
        travelGuide.foods?.let { foodList ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.foods_feature_title), style = Typography.titleLarge)
            foodList.forEach { food ->
                FoodItem(
                    modifier = Modifier.fillMaxWidth(),
                    food = food
                )
            }
        }
        travelGuide.cost?.let { cost ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.cost_feature_title), style = Typography.titleLarge)
            Card {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = cost,
                    style = Typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        if (!isSaved) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { openSaveDialog = true }
            ) {
                Text(text = "Save Guide", style = Typography.titleMedium)
            }
        }
    }
    if (openSaveDialog) {
        SaveDialog(
            onDismiss = { openSaveDialog = false },
            onConfirm = { label ->
                viewModel.saveTravelGuide(
                    label = label,
                    travelGuide = travelGuide,
                    onResult = {
                        if (it) {
                            openSaveDialog = false
                        } else {
                            Toast.makeText(context, "Wrong!", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        )
    }
}