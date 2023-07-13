package com.example.crowdfundingplatform.presentation.ui.screen.balance

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.theme.LabelItalicStyle
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemBorderColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemBorderErrorColor
import com.example.crowdfundingplatform.presentation.ui.theme.LoginItemContainerColor
import com.example.crowdfundingplatform.presentation.ui.theme.PrimaryTextColor
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentMedium
import com.example.crowdfundingplatform.presentation.viewmodel.PaymentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromoCodeTextField(
    paymentViewModel: PaymentViewModel,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = paymentViewModel.promoCode.value,
        onValueChange = paymentViewModel::setPromoCode,
        label = {
            Text(
                text = stringResource(id = R.string.promoCode),
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
        shape = RoundedCornerShape(percent = RoundedCornerShapePercentMedium),
        singleLine = true
    )
}