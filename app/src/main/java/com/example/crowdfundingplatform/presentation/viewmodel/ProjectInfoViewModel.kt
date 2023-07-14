package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.project.ProjectInfo
import com.example.crowdfundingplatform.domain.usecase.project.FundProjectUseCase
import com.example.crowdfundingplatform.domain.usecase.project.GetProjectInfoUseCase
import com.example.crowdfundingplatform.domain.usecase.auth.RefreshTokensUseCase
import com.example.crowdfundingplatform.presentation.common.ErrorCodes
import com.example.crowdfundingplatform.presentation.uistate.project.FundProjectState
import com.example.crowdfundingplatform.presentation.uistate.project.ProjectInfoState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Locale

class ProjectInfoViewModel(
    private val projectId: String,
    private val getProjectInfoUseCase: GetProjectInfoUseCase,
    private val fundProjectUseCase: FundProjectUseCase,
    private val refreshTokensUseCase: RefreshTokensUseCase
) : ViewModel() {

    val projectInfoState: State<ProjectInfoState>
        get() = _projectInfoState
    private val _projectInfoState: MutableState<ProjectInfoState> =
        mutableStateOf(ProjectInfoState.Loading)

    val projectFundState: State<FundProjectState>
        get() = _projectFundState
    private val _projectFundState: MutableState<FundProjectState> =
        mutableStateOf(FundProjectState.Input(Constants.EMPTY_STRING))

    private val fetchProjectExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.NOT_FOUND -> _projectInfoState.value = ProjectInfoState.Error(R.string.projectNotFound)
                else -> _projectInfoState.value = ProjectInfoState.Error(R.string.unknownError)
            }

            else -> _projectInfoState.value = ProjectInfoState.Error(R.string.unknownError)
        }
    }

    private val secondFundAttemptExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                ErrorCodes.BAD_REQUEST -> _projectFundState.value = FundProjectState.Error(R.string.insufficientMoney)

                ErrorCodes.UNAUTHORIZED -> _projectFundState.value = FundProjectState.SignedOut

                else -> _projectFundState.value = FundProjectState.Error(R.string.unknownError)
            }

            else -> _projectFundState.value = FundProjectState.Error(R.string.unknownError)
        }
    }

    private fun fundProjectExceptionHandler(moneyAmount: BigDecimal, projectId: String) =
        CoroutineExceptionHandler { _, exception ->
            when (exception) {
                is HttpException -> when (exception.code()) {
                    ErrorCodes.BAD_REQUEST -> _projectFundState.value =
                        FundProjectState.Error(R.string.insufficientMoney)

                    ErrorCodes.UNAUTHORIZED -> viewModelScope.launch(Dispatchers.IO + secondFundAttemptExceptionHandler) {
                        refreshTokensUseCase()
                        val project = fundProjectUseCase(projectId, moneyAmount).convertProjectTimeFields()
                        _projectFundState.value = FundProjectState.Success
                        _projectInfoState.value = ProjectInfoState.Content(project)
                    }

                    else -> _projectFundState.value = FundProjectState.Error(R.string.unknownError)
                }

                else -> _projectFundState.value = FundProjectState.Error(R.string.unknownError)
            }
        }

    init {
        getProject()
    }

    fun getProject() {
        _projectInfoState.value = ProjectInfoState.Loading
        viewModelScope.launch(Dispatchers.IO + fetchProjectExceptionHandler) {
            val project = getProjectInfoUseCase(projectId).convertProjectTimeFields()
            _projectInfoState.value = ProjectInfoState.Content(project)
        }
    }

    private fun ProjectInfo.convertProjectTimeFields(): ProjectInfo {
        val inputTimeFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val finishDate = inputTimeFormat.parse(finishDate)
        val startDate = inputTimeFormat.parse(creationDate)
        val outputTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return copy(
            creationDate = outputTimeFormat.format(startDate),
            finishDate = outputTimeFormat.format(finishDate)
        )
    }

    fun setFundMoneyAmount(amount: String) {
        if (_projectFundState.value is FundProjectState.Input) {
            _projectFundState.value =
                (_projectFundState.value as FundProjectState.Input).copy(moneyAmount = amount)
        }
    }

    fun fundProject() {
        if (_projectInfoState.value is ProjectInfoState.Content && _projectFundState.value is FundProjectState.Input) {
            val moneyAmount =
                (_projectFundState.value as FundProjectState.Input).moneyAmount.toBigDecimalOrNull()
            if (moneyAmount == null) {
                _projectFundState.value = FundProjectState.Error(R.string.invalidFundAmount)
                return
            }
            _projectFundState.value = FundProjectState.Loading
            val projectId = (_projectInfoState.value as ProjectInfoState.Content).projectInfo.id
            viewModelScope.launch(
                Dispatchers.IO + fundProjectExceptionHandler(
                    moneyAmount, projectId
                )
            ) {
                val project = fundProjectUseCase(projectId, moneyAmount).convertProjectTimeFields()
                _projectFundState.value = FundProjectState.Success
                _projectInfoState.value = ProjectInfoState.Content(project)
            }
        }
    }

    fun resetFundingState() {
        _projectFundState.value = FundProjectState.Input(Constants.EMPTY_STRING)
    }

}