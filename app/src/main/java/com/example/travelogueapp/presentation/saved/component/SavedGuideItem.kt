package com.example.travelogueapp.presentation.saved.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun SavedGuideItem(
    modifier: Modifier = Modifier,
    label: String,
    onClickItem: () -> Unit,
    onClickDeleteButton: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClickItem() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f).padding(10.dp),
                text = label,
                style = Typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Box(modifier = Modifier.background(Color(0xFFD22B2B))) {
                IconButton(onClick = { onClickDeleteButton() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete_icon"
                    )
                }
            }
        }
    }
}