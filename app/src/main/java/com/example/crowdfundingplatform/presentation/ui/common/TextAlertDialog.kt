package com.example.crowdfundingplatform.presentation.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyle
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle

@Composable
fun TextAlertDialog(
    title: String,
    text: String,
    dismissText: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(onDismissRequest = onDismiss, title = {
        Text(text = title, style = Subtitle, color = MaterialTheme.colorScheme.onBackground)
    }, text = {
        Text(text = text, style = LabelRegularStyle, color = MaterialTheme.colorScheme.onBackground)
    }, confirmButton = {
        Button(
            onClick = onDismiss
        ) {
            Text(
                text = dismissText, style = LabelRegularStyle, color = MaterialTheme.colorScheme.onBackground
            )
        }
    })
}