package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.usecase.ActivatePromoCodeUseCase
import com.example.crowdfundingplatform.domain.usecase.RefreshTokensUseCase
import com.example.crowdfundingplatform.presentation.uistate.payment.PaymentState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PaymentViewModel(
    private val refreshTokensUseCase: RefreshTokensUseCase,
    private val activatePromoCodeUseCase: ActivatePromoCodeUseCase
) : ViewModel() {

    private val _promoCode: MutableState<String> = mutableStateOf("")
    val promoCode: State<String>
        get() = _promoCode

    private val _paymentState: MutableState<PaymentState> = mutableStateOf(PaymentState.Input)
    val paymentState: State<PaymentState>
        get() = _paymentState

    val errorMessageFlow: SharedFlow<Int>
        get() = _errorMessageFlow
    private val _errorMessageFlow = MutableSharedFlow<Int>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val activationExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> {
                    viewModelScope.launch(Dispatchers.IO + secondActivationExceptionHandler) {
                        refreshTokensUseCase()
                        activatePromoCodeUseCase(_promoCode.value)
                        _paymentState.value = PaymentState.Success
                    }
                }
                403 -> _errorMessageFlow.tryEmit(R.string.forbidden)
                404 -> _errorMessageFlow.tryEmit(R.string.noSuchPromoCode)
                else -> _errorMessageFlow.tryEmit(R.string.unknownError)
            }
            else -> _errorMessageFlow.tryEmit(R.string.unknownError)
        }
        _paymentState.value = PaymentState.Input
    }

    private val secondActivationExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> _errorMessageFlow.tryEmit(R.string.unauthorized)
                403 -> _errorMessageFlow.tryEmit(R.string.forbidden)
                404 -> _errorMessageFlow.tryEmit(R.string.noSuchPromoCode)
                else -> _errorMessageFlow.tryEmit(R.string.unknownError)
            }
            else -> _errorMessageFlow.tryEmit(R.string.unknownError)
        }
        _paymentState.value = PaymentState.Input
    }

    fun setPromoCode(promoCode: String) {
        _promoCode.value = promoCode
    }

    fun activatePromoCode() {
        _paymentState.value = PaymentState.Loading
        viewModelScope.launch(Dispatchers.IO + activationExceptionHandler) {
            activatePromoCodeUseCase(_promoCode.value)
            _paymentState.value = PaymentState.Success
        }
    }

}