package com.example.travelogueapp.presentation.generate.component

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelDatePicker(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = { Button(onClick = {
            datePickerState.selectedDateMillis?.let {
                onConfirm(SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(it))
            }
            onDismiss()
        }) {
            Text(text = "Ok")
        } },
        content = {
            DatePicker(state = datePickerState)
        }
    )
}