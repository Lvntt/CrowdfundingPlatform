package com.example.crowdfundingplatform.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.usecase.GetProjectInfoUseCase
import com.example.crowdfundingplatform.presentation.uistate.ProjectInfoState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProjectInfoViewModel(
    private val projectId: String, private val getProjectInfoUseCase: GetProjectInfoUseCase
) : ViewModel() {

    val projectInfoState: State<ProjectInfoState>
        get() = _projectInfoState
    private val _projectInfoState: MutableState<ProjectInfoState> =
        mutableStateOf(ProjectInfoState.Loading)

    private val fetchProjectExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                404 -> _projectInfoState.value = ProjectInfoState.Error(R.string.projectNotFound)
                else -> _projectInfoState.value = ProjectInfoState.Error(R.string.unknownError)
            }

            else -> _projectInfoState.value = ProjectInfoState.Error(R.string.unknownError)
        }
    }

    fun getProject() {
        _projectInfoState.value = ProjectInfoState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val project = getProjectInfoUseCase(projectId)
            _projectInfoState.value = ProjectInfoState.Content(project)
        }
    }

}