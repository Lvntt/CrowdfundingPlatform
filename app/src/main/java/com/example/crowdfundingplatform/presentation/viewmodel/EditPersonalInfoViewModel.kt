package com.example.crowdfundingplatform.presentation.viewmodel

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.user.EditProfileRequest
import com.example.crowdfundingplatform.domain.usecase.user.EditYourProfileUseCase
import com.example.crowdfundingplatform.domain.usecase.user.GetYourProfileUseCase
import com.example.crowdfundingplatform.domain.usecase.auth.RefreshTokensUseCase
import com.example.crowdfundingplatform.domain.usecase.file.UploadFileAndGetIdUseCase
import com.example.crowdfundingplatform.presentation.common.ErrorCodes
import com.example.crowdfundingplatform.presentation.uistate.profile.AvatarUploadState
import com.example.crowdfundingplatform.presentation.uistate.profile.EditProfileInfoState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EditPersonalInfoViewModel(
    private val getYourProfileUseCase: GetYourProfileUseCase,
    private val editYourProfileUseCase: EditYourProfileUseCase,
    private val uploadFileAndGetIdUseCase: UploadFileAndGetIdUseCase,
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
            Constants.EMPTY_STRING,
            null
        )
    )

    val avatarUploadState: State<AvatarUploadState>
        get() = _avatarUploadState
    private val _avatarUploadState: MutableState<AvatarUploadState> = mutableStateOf(
        AvatarUploadState.Initial)

    private val fetchProfileExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.UNAUTHORIZED -> {
                    viewModelScope.launch(Dispatchers.IO + secondFetchAttemptExceptionHandler) {
                        refreshTokensUseCase()
                        val user = getYourProfileUseCase()
                        _editRequest.value = EditProfileRequest(
                            user.name, user.surname, user.patronymic, user.bio, user.avatarId
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
                ErrorCodes.UNAUTHORIZED -> {
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
                ErrorCodes.BAD_REQUEST -> _editInfoState.value = EditProfileInfoState.Error(R.string.invalidPersonalInfo)
                ErrorCodes.UNAUTHORIZED -> {
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

    fun uploadAvatar(uri: Uri) {
        _avatarUploadState.value = AvatarUploadState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val avatarId = uploadFileAndGetIdUseCase(uri)
                _editRequest.value = _editRequest.value.copy(avatarId = avatarId)
                _avatarUploadState.value = AvatarUploadState.Success
            } catch (e: Exception) {
                _avatarUploadState.value = AvatarUploadState.Error
            }
        }
    }

    fun removeAvatar() {
        _editRequest.value = _editRequest.value.copy(avatarId = null)
    }

    fun getProfile() {
        _editInfoState.value = EditProfileInfoState.Loading
        viewModelScope.launch(Dispatchers.IO + fetchProfileExceptionHandler) {
            val user = getYourProfileUseCase()
            _editRequest.value = EditProfileRequest(
                user.name, user.surname, user.patronymic, user.bio, user.avatarId
            )
            _editInfoState.value = EditProfileInfoState.Input
        }
    }

    fun applyEdit() {
        if (_avatarUploadState.value != AvatarUploadState.Loading) {
            _editInfoState.value = EditProfileInfoState.Loading
            viewModelScope.launch(Dispatchers.IO + editProfileExceptionHandler) {
                editYourProfileUseCase(_editRequest.value)
                _editInfoState.value = EditProfileInfoState.Success
            }
        }
    }

}