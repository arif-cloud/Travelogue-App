package com.example.travelogueapp.presentation.generate.component.loading

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.sharp.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun GeneratedFeatureItem(
    modifier: Modifier = Modifier,
    featureText: String,
    isCreated: Boolean
) {
    val contentColor = if (isCreated) MaterialTheme.colorScheme.primary else Color.Gray
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "check_icon",
            tint = contentColor
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = featureText,
            style = Typography.titleLarge,
            color = contentColor
        )
    }
}