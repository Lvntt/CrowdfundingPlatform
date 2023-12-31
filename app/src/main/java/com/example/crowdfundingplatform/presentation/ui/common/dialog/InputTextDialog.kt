package com.example.crowdfundingplatform.presentation.ui.common.dialog

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.crowdfundingplatform.presentation.ui.common.profile.EditFieldItem
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle

@Composable
fun InputTextDialog(
    title: String,
    editFieldLabel: String,
    text: String,
    onTextValueChange: (String) -> Unit,
    onConfirm: () -> Unit,
    confirmText: String,
    onDismiss: () -> Unit,
    dismissText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    AlertDialog(onDismissRequest = onDismiss, title = {
        Text(text = title, style = Subtitle, color = MaterialTheme.colorScheme.onBackground)
    }, text = {
        EditFieldItem(
            label = editFieldLabel,
            onValueChange = onTextValueChange,
            textFieldValue = text,
            keyboardOptions = keyboardOptions
        )
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
        Button(
            onClick = onDismiss
        ) {
            Text(
                text = dismissText,
                style = LabelBoldStyle,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    })
}