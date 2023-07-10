package com.example.crowdfundingplatform.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.crowdfundingplatform.presentation.ui.theme.ProgressDialogSize
import com.example.crowdfundingplatform.presentation.ui.theme.ProgressIndicatorSize
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonLargeCornerRadius

@Composable
fun LoadingProgress() {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .size(ProgressDialogSize)
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
                    TextButtonLargeCornerRadius))
        ) {
            CircularProgressIndicator(modifier = Modifier.size(ProgressIndicatorSize))
        }
    }
}