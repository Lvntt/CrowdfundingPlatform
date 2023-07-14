package com.example.crowdfundingplatform.presentation.ui.screen.creation.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.presentation.ui.theme.LabelItalicStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemBorderColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemBorderErrorColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemContainerColor
import com.example.crowdfundingplatform.presentation.ui.theme.PrimaryTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectCreationItem(
    label: String,
    modifier: Modifier = Modifier,
    textFieldValue: String = Constants.EMPTY_STRING,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textFieldShape: Shape = RoundedCornerShape(percent = RoundedCornerShapePercentMedium)
) {
    OutlinedTextField(
        value = textFieldValue,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = PrimaryTextColor,
                style = LabelItalicStyle
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = LoginItemContainerColor,
            textColor = PrimaryTextColor,
            unfocusedBorderColor = LoginItemBorderColor,
            focusedBorderColor = LoginItemBorderColor,
            errorBorderColor = LoginItemBorderErrorColor
        ),
        modifier = modifier.fillMaxWidth(),
        shape = textFieldShape,
        keyboardOptions = keyboardOptions,
        enabled = enabled,
        singleLine = singleLine
    )
}