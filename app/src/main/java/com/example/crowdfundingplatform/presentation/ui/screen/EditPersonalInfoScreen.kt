package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.domain.entity.EditProfileRequest
import com.example.crowdfundingplatform.presentation.ui.common.EditFieldItem
import com.example.crowdfundingplatform.presentation.ui.common.ErrorScreen
import com.example.crowdfundingplatform.presentation.ui.common.LoadingProgress
import com.example.crowdfundingplatform.presentation.ui.common.TextButton
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonSmallStyle
import com.example.crowdfundingplatform.presentation.uistate.EditProfileInfoState
import com.example.crowdfundingplatform.presentation.viewmodel.EditPersonalInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditPersonalInfoScreen(
    onSignedOut: () -> Unit,
    onSuccess: () -> Unit
) {
    val viewModel: EditPersonalInfoViewModel = koinViewModel()
    val state by remember { viewModel.editInfoState }
    val editRequest by remember { viewModel.editRequest }
    Crossfade(targetState = state, label = "") {
        when(it) {
            is EditProfileInfoState.Error -> ErrorScreen(messageId = it.messageId, onRetryClick = viewModel::getProfile)
            EditProfileInfoState.Input -> EditPersonalInfoBody(
                editProfileRequest = editRequest,
                onNameChange = viewModel::setName,
                onSurnameChange = viewModel::setSurname,
                onPatronymicChange = viewModel::setPatronymic,
                onBioChange = viewModel::setBio,
                onConfirmClick = viewModel::applyEdit
            )
            EditProfileInfoState.Loading -> LoadingProgress()
            EditProfileInfoState.SignedOut -> LaunchedEffect(Unit) { onSignedOut() }
            EditProfileInfoState.Success -> LaunchedEffect(Unit) { onSuccess() }
            }
    }
}

@Composable
fun EditPersonalInfoBody(
    editProfileRequest: EditProfileRequest,
    onNameChange: (String) -> Unit,
    onSurnameChange: (String) -> Unit,
    onPatronymicChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
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