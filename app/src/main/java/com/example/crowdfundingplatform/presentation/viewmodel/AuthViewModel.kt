package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.entity.LoginRequest
import com.example.crowdfundingplatform.domain.entity.RegisterRequest
import com.example.crowdfundingplatform.domain.usecase.LoginUserUseCase
import com.example.crowdfundingplatform.domain.usecase.RegisterUserUseCase
import com.example.crowdfundingplatform.presentation.uistate.AuthUiState
import com.example.crowdfundingplatform.presentation.uistate.LoginContent
import com.example.crowdfundingplatform.presentation.uistate.RegistrationContent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {
    val authState: State<AuthUiState>
        get() = _authState
    private val _authState: MutableState<AuthUiState> = mutableStateOf(AuthUiState.Input)

    val loginContent: State<LoginContent>
        get() = _loginContent
    private val _loginContent = mutableStateOf(LoginContent("", ""))

    val registrationContent: State<RegistrationContent>
        get() = _registrationContent
    private val _registrationContent = mutableStateOf(RegistrationContent("", "", "", "", "", ""))

    private val signupExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                400 -> _authState.value = AuthUiState.Error(R.string.invalidForm)
                409 -> _authState.value = AuthUiState.Error(R.string.userExists)
                else -> _authState.value = AuthUiState.Error(R.string.unknownError)
            }

            else -> _authState.value = AuthUiState.Error(R.string.unknownError)
        }
    }
    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> _authState.value = AuthUiState.Error(R.string.invalidCredentials)
                else -> _authState.value = AuthUiState.Error(R.string.unknownError)
            }

            else -> _authState.value = AuthUiState.Error(R.string.unknownError)
        }
    }

    fun setName(name: String) {
        _registrationContent.value = _registrationContent.value.copy(name = name)
    }

    fun setSurname(surname: String) {
        _registrationContent.value = _registrationContent.value.copy(surname = surname)
    }

    fun setPatronymic(patronymic: String) {
        _registrationContent.value = _registrationContent.value.copy(patronymic = patronymic)
    }

    fun setEmail(email: String) {
        _registrationContent.value = _registrationContent.value.copy(email = email)
    }

    fun setPassword(password: String) {
        _registrationContent.value = _registrationContent.value.copy(password = password)
    }

    fun setConfirmPassword(confirmPassword: String) {
        _registrationContent.value =
            _registrationContent.value.copy(confirmPassword = confirmPassword)
    }

    fun setLoginEmail(email: String) {
        _loginContent.value = _loginContent.value.copy(email = email)
    }

    fun setLoginPassword(password: String) {
        _loginContent.value = _loginContent.value.copy(password = password)
    }

    fun signUp() {
        _authState.value = AuthUiState.Loading
        viewModelScope.launch(Dispatchers.IO + signupExceptionHandler) {
            registerUserUseCase.execute(
                RegisterRequest(
                    _registrationContent.value.name,
                    _registrationContent.value.surname,
                    _registrationContent.value.patronymic,
                    _registrationContent.value.password,
                    _registrationContent.value.email
                )
            )
            _authState.value = AuthUiState.Success
        }
    }

    fun logIn() {
        _authState.value = AuthUiState.Loading
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            loginUserUseCase.execute(
                LoginRequest(
                    _loginContent.value.email, _loginContent.value.password
                )
            )
            _authState.value = AuthUiState.Success
        }
    }

    fun resetErrorState() {
        if (_authState.value is AuthUiState.Error) {
            _authState.value = AuthUiState.Input
        }
    }
}