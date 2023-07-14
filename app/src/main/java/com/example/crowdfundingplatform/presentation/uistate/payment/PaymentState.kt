package com.example.crowdfundingplatform.presentation.uistate.payment

sealed interface PaymentState {

    object Input : PaymentState

    object Loading : PaymentState

    object Success : PaymentState

}