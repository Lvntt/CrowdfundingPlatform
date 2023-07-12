package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.EditProfileRequest
import com.example.crowdfundingplatform.domain.usecase.EditYourProfileUseCase
import com.example.crowdfundingplatform.domain.usecase.GetYourProfileUseCase
import com.example.crowdfundingplatform.domain.usecase.RefreshTokensUseCase
import com.example.crowdfundingplatform.presentation.uistate.EditProfileInfoState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EditPersonalInfoViewModel(
    private val getYourProfileUseCase: GetYourProfileUseCase,
    private val editYourProfileUseCase: EditYourProfileUseCase,
    private val refreshTokensUseCase: RefreshTokensUseCase
) : ViewModel() {
    val editInfoState: State<EditProfileInfoState>
        get() = _editInfoState
    private val _editInfoState: MutableState<EditProfileInfoState> =
        mutableStateOf(EditProfileInfoState.Loading)

    val editRequest: State<EditProfileRequest>
        get() = _editRequest
    private val _editRequest: MutableState<EditProfileRequest> = mutableStateOf(
        EditProfileRequest(
            Constants.EMPTY_STRING,
            Constants.EMPTY_STRING,
            Constants.EMPTY_STRING,
            Constants.EMPTY_STRING
        )
    )

    private val fetchProfileExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> {
                    viewModelScope.launch(Dispatchers.IO + secondFetchAttemptExceptionHandler) {
                        refreshTokensUseCase()
                        val user = getYourProfileUseCase()
                        _editRequest.value = EditProfileRequest(
                            user.name, user.surname, user.patronymic, user.bio
                        )
                        _editInfoState.value = EditProfileInfoState.Input
                    }
                }

                else -> _editInfoState.value = EditProfileInfoState.Error(R.string.unknownError)
            }

            else -> _editInfoState.value = EditProfileInfoState.Error(R.string.unknownError)
        }
    }

    private val secondFetchAttemptExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> {
                    _editInfoState.value = EditProfileInfoState.SignedOut
                }

                else -> _editInfoState.value = EditProfileInfoState.Error(R.string.unknownError)
            }

            else -> _editInfoState.value = EditProfileInfoState.Error(R.string.unknownError)
        }
    }

    private val editProfileExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                400 -> _editInfoState.value = EditProfileInfoState.Error(R.string.invalidPersonalInfo)
                401 -> {
                    viewModelScope.launch(Dispatchers.IO + secondFetchAttemptExceptionHandler) {
                        refreshTokensUseCase()
                        editYourProfileUseCase(_editRequest.value)
                        _editInfoState.value = EditProfileInfoState.Success
                    }
                }

                else -> _editInfoState.value = EditProfileInfoState.Error(R.string.unknownError)
            }

            else -> _editInfoState.value = EditProfileInfoState.Error(R.string.unknownError)
        }
    }

    init {
        getProfile()
    }

    fun setName(name: String) {
        _editRequest.value = _editRequest.value.copy(name = name)
    }

    fun setSurname(surname: String) {
        _editRequest.value = _editRequest.value.copy(surname = surname)
    }

    fun setPatronymic(patronymic: String) {
        _editRequest.value = _editRequest.value.copy(patronymic = patronymic)
    }

    fun setBio(bio: String) {
        _editRequest.value = _editRequest.value.copy(bio = bio)
    }

    fun getProfile() {
        _editInfoState.value = EditProfileInfoState.Loading
        viewModelScope.launch(Dispatchers.IO + fetchProfileExceptionHandler) {
            val user = getYourProfileUseCase()
            _editRequest.value = EditProfileRequest(
                user.name, user.surname, user.patronymic, user.bio
            )
            _editInfoState.value = EditProfileInfoState.Input
        }
    }

    fun applyEdit() {
        _editInfoState.value = EditProfileInfoState.Loading
        viewModelScope.launch(Dispatchers.IO + editProfileExceptionHandler) {
            editYourProfileUseCase(_editRequest.value)
            _editInfoState.value = EditProfileInfoState.Success
        }
    }

}