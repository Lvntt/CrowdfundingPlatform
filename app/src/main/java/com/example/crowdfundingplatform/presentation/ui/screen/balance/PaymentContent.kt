package com.example.crowdfundingplatform.presentation.ui.screen.balance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingLarge
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.viewmodel.PaymentViewModel

@Composable
fun PaymentContent(
    paymentViewModel: PaymentViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingMedium)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.activatePromoCode),
            style = Subtitle
        )

        Spacer(modifier = Modifier.height(PaddingMedium))

        PromoCodeTextField(paymentViewModel = paymentViewModel)

        Spacer(modifier = Modifier.height(PaddingLarge))

        ActivateButton(
            modifier = Modifier.align(Alignment.End),
            onClick = paymentViewModel::activatePromoCode
        )
    }
}