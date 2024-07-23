package com.example.travelogueapp.presentation.travel_guide.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.travelogueapp.ui.theme.Typography

@Composable
fun SaveDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    var label by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = { onConfirm(label) }) {
                Text(text = "Save", style = Typography.titleSmall)
            }
        },
        text = {
            TextField(
                value = label,
                onValueChange = { label = it },
                placeholder = { Text(text = "Enter label") }
            )
        }
    )
}