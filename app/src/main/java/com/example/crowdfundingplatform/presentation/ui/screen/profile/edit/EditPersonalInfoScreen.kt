package com.example.crowdfundingplatform.presentation.ui.screen.profile.edit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.domain.entity.user.EditProfileRequest
import com.example.crowdfundingplatform.presentation.ui.common.profile.EditFieldItem
import com.example.crowdfundingplatform.presentation.ui.common.state.ErrorScreen
import com.example.crowdfundingplatform.presentation.ui.common.state.LoadingProgress
import com.example.crowdfundingplatform.presentation.ui.common.button.TextButton
import com.example.crowdfundingplatform.presentation.ui.common.button.UploadButton
import com.example.crowdfundingplatform.presentation.ui.theme.ExtraSmallLabelRegularStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonSmallStyle
import com.example.crowdfundingplatform.presentation.uistate.profile.AvatarUploadState
import com.example.crowdfundingplatform.presentation.uistate.profile.EditProfileInfoState
import com.example.crowdfundingplatform.presentation.viewmodel.EditPersonalInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditPersonalInfoScreen(
    onSignedOut: () -> Unit, onSuccess: () -> Unit
) {
    val viewModel: EditPersonalInfoViewModel = koinViewModel()
    val state by remember { viewModel.editInfoState }
    val editRequest by remember { viewModel.editRequest }
    val imageUploadState by remember { viewModel.avatarUploadState }
    Crossfade(targetState = state, label = "") {
        when (it) {
            is EditProfileInfoState.Error -> ErrorScreen(
                messageId = it.messageId, onRetryClick = viewModel::getProfile
            )

            EditProfileInfoState.Input -> EditPersonalInfoContent(
                editProfileRequest = editRequest,
                onNameChange = viewModel::setName,
                onSurnameChange = viewModel::setSurname,
                onPatronymicChange = viewModel::setPatronymic,
                onBioChange = viewModel::setBio,
                onConfirmClick = viewModel::applyEdit,
                onUploadAvatar = viewModel::uploadAvatar,
                onRemoveAvatar = viewModel::removeAvatar,
                imageUploadState = imageUploadState
            )

            EditProfileInfoState.Loading -> LoadingProgress()
            EditProfileInfoState.SignedOut -> LaunchedEffect(Unit) { onSignedOut() }
            EditProfileInfoState.Success -> LaunchedEffect(Unit) { onSuccess() }
        }
    }
}

@Composable
fun EditPersonalInfoContent(
    editProfileRequest: EditProfileRequest,
    imageUploadState: AvatarUploadState,
    onNameChange: (String) -> Unit,
    onSurnameChange: (String) -> Unit,
    onPatronymicChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onUploadAvatar: (Uri) -> Unit,
    onRemoveAvatar: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                onUploadAvatar(uri)
            }
        }
    Column(
        modifier = modifier.verticalScroll(
                rememberScrollState()
            ), verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        Spacer(modifier = Modifier.height(PaddingMedium))
        EditFieldItem(
            label = stringResource(id = R.string.name),
            textFieldValue = editProfileRequest.name,
            onValueChange = onNameChange
        )
        EditFieldItem(
            label = stringResource(id = R.string.surname),
            textFieldValue = editProfileRequest.surname,
            onValueChange = onSurnameChange
        )
        EditFieldItem(
            label = stringResource(id = R.string.patronymic),
            textFieldValue = editProfileRequest.patronymic,
            onValueChange = onPatronymicChange
        )
        EditFieldItem(
            label = stringResource(id = R.string.bio),
            textFieldValue = editProfileRequest.bio,
            onValueChange = onBioChange
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            UploadButton(
                onClick = { launcher.launch("image/*") },
                text = stringResource(id = R.string.uploadAvatar)
            )
            UploadButton(
                onClick = onRemoveAvatar,
                text = stringResource(id = R.string.deleteAvatar),
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                containerColor = MaterialTheme.colorScheme.errorContainer,
                iconId = R.drawable.delete_icon
            )
        }
        if (imageUploadState != AvatarUploadState.Initial) {
            Text(
                text = when (imageUploadState) {
                    AvatarUploadState.Error -> stringResource(id = R.string.unableToGetImage)
                    AvatarUploadState.Initial -> Constants.EMPTY_STRING
                    AvatarUploadState.Success -> stringResource(id = R.string.avatarSuccess)
                    AvatarUploadState.Loading -> stringResource(id = R.string.uploadingAvatar)
                },
                style = ExtraSmallLabelRegularStyleAlternative,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = PaddingMedium),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                text = stringResource(id = R.string.confirm),
                buttonTextStyle = TextButtonSmallStyle,
                onClick = onConfirmClick
            )
        }
        Spacer(modifier = Modifier.height(PaddingMedium))
    }
}