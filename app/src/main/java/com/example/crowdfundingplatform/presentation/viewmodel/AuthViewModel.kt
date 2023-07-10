package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.entity.LoginRequest
import com.example.crowdfundingplatform.domain.entity.RegisterRequest
import com.example.crowdfundingplatform.domain.usecase.CheckTokenExistenceUseCase
import com.example.crowdfundingplatform.domain.usecase.LoginUserUseCase
import com.example.crowdfundingplatform.domain.usecase.RegisterUserUseCase
import com.example.crowdfundingplatform.presentation.uistate.AuthUiState
import com.example.crowdfundingplatform.presentation.uistate.CrowdfundingAppState
import com.example.crowdfundingplatform.presentation.uistate.LoginContent
import com.example.crowdfundingplatform.presentation.uistate.RegistrationContent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class AuthViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val checkTokenExistenceUseCase: CheckTokenExistenceUseCase,
    private var appState: CrowdfundingAppState
) : ViewModel() {
    val authState: State<AuthUiState>
        get() = _authState
    private val _authState: MutableState<AuthUiState> = mutableStateOf(AuthUiState.Initial)

    val loginContent: State<LoginContent>
        get() = _loginContent
    private val _loginContent = mutableStateOf(LoginContent("", ""))

    val registrationContent: State<RegistrationContent>
        get() = _registrationContent
    private val _registrationContent = mutableStateOf(RegistrationContent("", "", "", "", "", ""))

    private val signupExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                400 -> appState.showErrorDialog(R.string.invalidForm)
                409 -> appState.showErrorDialog(R.string.userExists)
                else -> appState.showErrorDialog(R.string.unknownError)
            }

            else -> appState.showErrorDialog(R.string.unknownError)
        }
        appState.hideLoadingProgress()
    }

    private val loginExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> appState.showErrorDialog(R.string.invalidCredentials)
                else -> appState.showErrorDialog(R.string.unknownError)
            }

            else -> appState.showErrorDialog(R.string.unknownError)
        }
        appState.hideLoadingProgress()
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (checkTokenExistenceUseCase()) {
                withContext(Dispatchers.Main) {
                    appState.navigateToHome()
                }
            } else {
                withContext(Dispatchers.Main) {
                    _authState.value = AuthUiState.Input
                }
            }
        }
    }

    fun setAppState(appState: CrowdfundingAppState) {
        this.appState = appState
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
        appState.showLoadingProgress()
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
            appState.hideLoadingProgress()
            withContext(Dispatchers.Main) {
                appState.navigateToHome()
            }
        }
    }

    fun logIn() {
        appState.showLoadingProgress()
        viewModelScope.launch(Dispatchers.IO + loginExceptionHandler) {
            loginUserUseCase(
                LoginRequest(
                    _loginContent.value.email, _loginContent.value.password
                )
            )
            appState.hideLoadingProgress()
            withContext(Dispatchers.Main) {
                appState.navigateToHome()
            }
        }
    }

}