package com.example.travelogueapp.presentation.saved.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun EmptyListWarning(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            imageVector = Icons.Outlined.Info,
            contentDescription = "warning_icon"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Guide List is Empty", style =  Typography.titleMedium)
    }
}