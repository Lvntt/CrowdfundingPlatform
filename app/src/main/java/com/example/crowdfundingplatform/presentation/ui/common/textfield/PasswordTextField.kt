package com.example.crowdfundingplatform.presentation.ui.common.textfield

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.presentation.ui.theme.IconDefaultTintColor
import com.example.crowdfundingplatform.presentation.ui.theme.LabelItalicStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemBorderColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemBorderErrorColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemContainerColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemEndPadding
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PrimaryTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    label: String,
    textFieldValue: String = Constants.EMPTY_STRING,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.lock_icon),
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = LoginItemEndPadding),
            shape = RoundedCornerShape(percent = RoundedCornerShapePercentMedium),
            enabled = enabled,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image =
                    if (passwordVisible) ImageVector.vectorResource(id = R.drawable.visibility_icon)
                    else ImageVector.vectorResource(id = R.drawable.visibility_off_icon)

                val description =
                    if (passwordVisible) stringResource(id = R.string.hidePassword)
                    else stringResource(id = R.string.showPassword)

                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(imageVector = image, description)
                }
            },
            singleLine = true
        )
    }
}