package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.entity.auth.LoginRequest
import com.example.crowdfundingplatform.domain.entity.auth.RegisterRequest
import com.example.crowdfundingplatform.domain.usecase.auth.LoginUserUseCase
import com.example.crowdfundingplatform.domain.usecase.auth.RegisterUserUseCase
import com.example.crowdfundingplatform.presentation.common.ErrorCodes
import com.example.crowdfundingplatform.presentation.uistate.auth.AuthState
import com.example.crowdfundingplatform.presentation.uistate.auth.AuthType
import com.example.crowdfundingplatform.presentation.uistate.auth.LoginContent
import com.example.crowdfundingplatform.presentation.uistate.auth.RegistrationContent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AuthViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {
    val authState: State<AuthState>
        get() = _authState
    private val _authState: MutableState<AuthState> = mutableStateOf(AuthState.Input)

    val errorMessageFlow: SharedFlow<Int>
        get() = _errorMessageFlow
    private val _errorMessageFlow = MutableSharedFlow<Int>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    val authType: State<AuthType>
        get() = _authType
    private val _authType = mutableStateOf(AuthType.LOG_IN)

    val loginContent: State<LoginContent>
        get() = _loginContent
    private val _loginContent = mutableStateOf(LoginContent("", ""))

    val registrationContent: State<RegistrationContent>
        get() = _registrationContent
    private val _registrationContent = mutableStateOf(RegistrationContent("", "", "", "", "", ""))

    private val signupExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.BAD_REQUEST -> _errorMessageFlow.tryEmit(R.string.invalidForm)
                ErrorCodes.CONFLICT -> _errorMessageFlow.tryEmit(R.string.userExists)
                else -> _errorMessageFlow.tryEmit(R.string.unknownError)
            }

            else -> _errorMessageFlow.tryEmit(R.string.unknownError)
        }
        _authState.value = AuthState.Input
    }

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.UNAUTHORIZED -> _errorMessageFlow.tryEmit(R.string.invalidCredentials)
                else -> _errorMessageFlow.tryEmit(R.string.unknownError)
            }

            else -> _errorMessageFlow.tryEmit(R.string.unknownError)
        }
        _authState.value = AuthState.Input
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
        _authState.value = AuthState.Loading
        viewModelScope.launch(Dispatchers.IO + signupExceptionHandler) {
            registerUserUseCase(
                RegisterRequest(
                    _registrationContent.value.name,
                    _registrationContent.value.surname,
                    _registrationContent.value.patronymic,
                    _registrationContent.value.password,
                    _registrationContent.value.email
                )
            )
            _authState.value = AuthState.Success
        }
    }

    fun logIn() {
        _authState.value = AuthState.Loading
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            loginUserUseCase(
                LoginRequest(
                    _loginContent.value.email, _loginContent.value.password
                )
            )
            _authState.value = AuthState.Success
        }
    }

    fun openRegistrationPersonalInfo() {
        _authType.value = AuthType.REGISTER_PERSONAL_INFO
    }

    fun openRegistrationCredentials() {
        _authType.value = AuthType.REGISTER_CREDENTIALS
    }

    fun openLogin() {
        _authType.value = AuthType.LOG_IN
    }

}