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

    val loginEmail: State<String>
        get() = _loginEmail
    private val _loginEmail = mutableStateOf("")
    val loginPassword: State<String>
        get() = _loginPassword
    private val _loginPassword = mutableStateOf("")

    val name: State<String>
        get() = _name
    private val _name = mutableStateOf("")
    val surname: State<String>
        get() = _surname
    private val _surname = mutableStateOf("")
    val patronymic: State<String>
        get() = _patronymic
    private val _patronymic = mutableStateOf("")
    val email: State<String>
        get() = _email
    private val _email = mutableStateOf("")
    val password: State<String>
        get() = _password
    private val _password = mutableStateOf("")
    val confirmPassword: State<String>
        get() = _confirmPassword
    private val _confirmPassword = mutableStateOf("")

    private val signupExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when(exception) {
            is HttpException ->
                when(exception.code()) {
                    400 -> _authState.value = AuthUiState.Error(R.string.invalidForm)
                    409 -> _authState.value = AuthUiState.Error(R.string.userExists)
                }
            else -> _authState.value = AuthUiState.Error(R.string.unknownError)
        }
    }
    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when(exception) {
            is HttpException ->
                when(exception.code()) {
                    401 -> _authState.value = AuthUiState.Error(R.string.invalidCredentials)
                }
            else -> _authState.value = AuthUiState.Error(R.string.unknownError)
        }
    }

    fun setName(name: String) {
        _name.value = name
    }

    fun setSurname(surname: String) {
        _surname.value = surname
    }

    fun setPatronymic(patronymic: String) {
        _patronymic.value = patronymic
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    fun setLoginEmail(email: String) {
        _loginEmail.value = email
    }

    fun setLoginPassword(password: String) {
        _loginPassword.value = password
    }

    fun signUp() {
        _authState.value = AuthUiState.Loading
        viewModelScope.launch(Dispatchers.IO + signupExceptionHandler) {
            registerUserUseCase.execute(
                RegisterRequest(
                    _name.value,
                    _surname.value,
                    _patronymic.value,
                    _password.value,
                    _email.value
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
                    _loginEmail.value,
                    _loginPassword.value
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