package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.usecase.user.GetYourProfileUseCase
import com.example.crowdfundingplatform.domain.usecase.auth.RefreshTokensUseCase
import com.example.crowdfundingplatform.presentation.common.ErrorCodes
import com.example.crowdfundingplatform.presentation.uistate.profile.ProfileInfoState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileInfoViewModel(
    private val getYourProfileUseCase: GetYourProfileUseCase,
    private val refreshTokensUseCase: RefreshTokensUseCase
) : ViewModel() {
    val profileInfoState: State<ProfileInfoState>
        get() = _profileInfoState
    private val _profileInfoState: MutableState<ProfileInfoState> = mutableStateOf(ProfileInfoState.Loading)

    private val fetchProfileExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when(exception) {
            is HttpException -> when(exception.code()) {
                ErrorCodes.UNAUTHORIZED -> {
                    viewModelScope.launch(Dispatchers.IO + secondFetchAttemptExceptionHandler) {
                        refreshTokensUseCase()
                        val user = getYourProfileUseCase()
                        _profileInfoState.value = ProfileInfoState.Content(user)
                    }
                }

                else -> _profileInfoState.value = ProfileInfoState.Error(R.string.unknownError)
            }

            else -> _profileInfoState.value = ProfileInfoState.Error(R.string.unknownError)
        }
    }

    private val secondFetchAttemptExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when(exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.UNAUTHORIZED -> {
                    _profileInfoState.value = ProfileInfoState.SignedOut
                }

                else -> _profileInfoState.value = ProfileInfoState.Error(R.string.unknownError)
            }

            else -> _profileInfoState.value = ProfileInfoState.Error(R.string.unknownError)
        }
    }

    init {
        getProfile()
    }

    fun getProfile() {
        _profileInfoState.value = ProfileInfoState.Loading
        viewModelScope.launch(Dispatchers.IO + fetchProfileExceptionHandler) {
            val user = getYourProfileUseCase()
            _profileInfoState.value = ProfileInfoState.Content(user)
        }
    }
}