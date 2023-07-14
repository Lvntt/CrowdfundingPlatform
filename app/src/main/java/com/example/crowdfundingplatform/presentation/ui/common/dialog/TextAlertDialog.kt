package com.example.crowdfundingplatform.presentation.ui.common.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyle
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle

@Composable
fun TextAlertDialog(
    title: String,
    text: String,
    confirmText: String,
    dismissText: String? = null,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(onDismissRequest = onDismiss, title = {
        Text(text = title, style = Subtitle, color = MaterialTheme.colorScheme.onBackground)
    }, text = {
        Text(text = text, style = LabelRegularStyle, color = MaterialTheme.colorScheme.onBackground)
    }, confirmButton = {
        Button(
            onClick = onConfirm
        ) {
            Text(
                text = confirmText,
                style = LabelBoldStyle,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }, dismissButton = {
        if (dismissText != null) {
            Button(
                onClick = onDismiss
            ) {
                Text(
                    text = dismissText,
                    style = LabelBoldStyle,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    })
}