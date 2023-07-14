package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.domain.usecase.auth.CheckTokenExistenceUseCase
import com.example.crowdfundingplatform.presentation.uistate.auth.OnboardingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OnboardingViewModel(
    private val checkTokenExistenceUseCase: CheckTokenExistenceUseCase
): ViewModel() {
    val onboardingState: State<OnboardingState>
        get() = _onboardingState
    private val _onboardingState: MutableState<OnboardingState> = mutableStateOf(OnboardingState.Initial)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkTokenExistenceUseCase()) {
                withContext(Dispatchers.Main) {
                    _onboardingState.value = OnboardingState.Success
                }
            } else {
                withContext(Dispatchers.Main) {
                    _onboardingState.value = OnboardingState.Content
                }
            }
        }
    }
}