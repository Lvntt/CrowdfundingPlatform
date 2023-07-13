package com.example.crowdfundingplatform.presentation.viewmodel


import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.ProjectCategory
import com.example.crowdfundingplatform.domain.entity.ProjectCreationRequest
import com.example.crowdfundingplatform.domain.usecase.CreateProjectUseCase
import com.example.crowdfundingplatform.domain.usecase.RefreshTokensUseCase
import com.example.crowdfundingplatform.domain.usecase.UploadFileAndGetIdUseCase
import com.example.crowdfundingplatform.presentation.ProjectCategoryToDescriptionRes
import com.example.crowdfundingplatform.presentation.uistate.creation.CreationType
import com.example.crowdfundingplatform.presentation.uistate.creation.ImageUploadState
import com.example.crowdfundingplatform.presentation.uistate.creation.Project
import com.example.crowdfundingplatform.presentation.uistate.creation.ProjectCreationState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ProjectCreationViewModel(
    private val uploadFileAndGetIdUseCase: UploadFileAndGetIdUseCase,
    private val createProjectUseCase: CreateProjectUseCase,
    private val refreshTokensUseCase: RefreshTokensUseCase
) : ViewModel() {

    private val _project = mutableStateOf(Project())
    val project: State<Project>
        get() = _project

    val errorMessageFlow: SharedFlow<Int>
        get() = _errorMessageFlow
    private val _errorMessageFlow = MutableSharedFlow<Int>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val _creationType = mutableStateOf(CreationType.SUMMARY)
    val creationType: State<CreationType>
        get() = _creationType

    private val _imageUploadState: MutableState<ImageUploadState> = mutableStateOf(ImageUploadState.Initial)
    val imageUploadState: State<ImageUploadState>
        get() = _imageUploadState

    private val _creationState: MutableState<ProjectCreationState> = mutableStateOf(ProjectCreationState.Input)
    val creationState: State<ProjectCreationState>
        get() = _creationState

    private val projectCreationExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> {
                    viewModelScope.launch(Dispatchers.IO + secondProjectCreationExceptionHandler) {
                        refreshTokensUseCase()
                        createProjectUseCase(
                            with(_project.value) {
                                ProjectCreationRequest(
                                    title = title,
                                    summary = summary,
                                    description = description,
                                    targetAmount = targetAmount.toLong(),
                                    category = category,
                                    finishDate = finishDate,
                                    avatarId = avatarId,
                                    attachmentIds = attachmentIds
                                )
                            }
                        )
                        _creationState.value = ProjectCreationState.Success
                    }
                }
                403 -> _errorMessageFlow.tryEmit(R.string.forbidden)
                409 -> _errorMessageFlow.tryEmit(R.string.sameProjectExists)
                else -> _errorMessageFlow.tryEmit(R.string.unknownError)
            }
            else -> _errorMessageFlow.tryEmit(R.string.unknownError)
        }
        _creationState.value = ProjectCreationState.Input
    }

    private val secondProjectCreationExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is HttpException -> when (exception.code()) {
                401 -> _errorMessageFlow.tryEmit(R.string.unauthorized)
                403 -> _errorMessageFlow.tryEmit(R.string.forbidden)
                409 -> _errorMessageFlow.tryEmit(R.string.sameProjectExists)
                else -> _errorMessageFlow.tryEmit(R.string.unknownError)
            }
            else -> _errorMessageFlow.tryEmit(R.string.unknownError)
        }
        _creationState.value = ProjectCreationState.Input
    }

    private fun getFinishDate(): String {
        val dateFormat = SimpleDateFormat(Constants.DATETIME_PATTERN, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, Constants.EXPIRES_AFTER_MONTHS)

        return dateFormat.format(calendar.time)
    }

    fun getCategoryDescription(category: ProjectCategory?): Int? {
        return if (category == null) {
            null
        } else {
            ProjectCategoryToDescriptionRes.descriptions[category]
        }
    }

    fun setProjectTitle(title: String) {
        _project.value = _project.value.copy(title = title)
    }

    fun setProjectSummary(summary: String) {
        _project.value = _project.value.copy(summary = summary)
    }

    fun setProjectTargetAmount(amount: String) {
        _project.value = _project.value.copy(targetAmount = amount)
    }

    fun setProjectAvatarId(uri: Uri) {
        viewModelScope.launch {
            try {
                val avatarId = uploadFileAndGetIdUseCase(uri)
                _project.value = _project.value.copy(avatarId = avatarId)
                _imageUploadState.value = ImageUploadState.Success
            } catch (e: Exception) {
                _imageUploadState.value = ImageUploadState.Error
            }
        }
    }

    fun setProjectCategory(category: ProjectCategory) {
        _project.value = _project.value.copy(category = category)
    }

    fun setProjectDescription(description: String) {
        _project.value = _project.value.copy(description = description)
    }

    fun openAvatar() {
        _creationType.value = CreationType.AVATAR
    }

    fun openCategory() {
        _creationType.value = CreationType.CATEGORY
        _imageUploadState.value = ImageUploadState.Initial
    }

    fun openDescription() {
        _creationType.value = CreationType.DESCRIPTION
    }

    fun setCreationType(type: CreationType) {
        _creationType.value = type
    }

    fun createProject() {
        val finishDate = getFinishDate()

        _creationState.value = ProjectCreationState.Loading
        viewModelScope.launch(Dispatchers.IO + projectCreationExceptionHandler) {
            createProjectUseCase(
                with(_project.value) {
                    ProjectCreationRequest(
                        title = title,
                        summary = summary,
                        description = description,
                        targetAmount = targetAmount.toLong(),
                        category = category,
                        finishDate = finishDate,
                        avatarId = avatarId,
                        attachmentIds = attachmentIds
                    )
                }
            )
            _creationState.value = ProjectCreationState.Success
        }
    }

}