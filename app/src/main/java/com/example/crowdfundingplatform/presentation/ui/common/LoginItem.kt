package com.example.crowdfundingplatform.presentation.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PrimaryTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemBorderColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemBorderErrorColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemContainerColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemEndPadding
import com.example.crowdfundingplatform.presentation.ui.theme.IconDefaultTintColor
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginItem(
    icon: ImageVector,
    label: String,
    textFieldValue: String = Constants.EMPTY_STRING,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Icon(
            imageVector = icon,
            tint = IconDefaultTintColor,
            contentDescription = null,
            modifier = Modifier.padding(PaddingMedium)
        )
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = label,
                    color = PrimaryTextColor,
                    style = LabelBoldStyle
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = LoginItemContainerColor,
                textColor = PrimaryTextColor,
                unfocusedBorderColor = LoginItemBorderColor,
                focusedBorderColor = LoginItemBorderColor,
                errorBorderColor = LoginItemBorderErrorColor
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = LoginItemEndPadding),
            shape = RoundedCornerShape(percent = RoundedCornerShapePercentMedium),
            enabled = enabled,
            singleLine = true
        )
    }
}