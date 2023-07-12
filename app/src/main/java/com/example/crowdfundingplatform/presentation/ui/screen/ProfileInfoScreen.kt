package com.example.crowdfundingplatform.presentation.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.ErrorScreen
import com.example.crowdfundingplatform.presentation.ui.common.LoadingProgress
import com.example.crowdfundingplatform.presentation.ui.common.ProfileInfoItem
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingLarge
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PrimaryColorLight
import com.example.crowdfundingplatform.presentation.ui.theme.ProfilePhotoBackgroundSize
import com.example.crowdfundingplatform.presentation.ui.theme.ProfilePhotoBorderSize
import com.example.crowdfundingplatform.presentation.ui.theme.ProfilePhotoSize
import com.example.crowdfundingplatform.presentation.ui.theme.TopAppBarStyle
import com.example.crowdfundingplatform.presentation.uistate.ProfileInfoState
import com.example.crowdfundingplatform.presentation.viewmodel.ProfileInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileInfoScreen(
    @DrawableRes
    photoId: Int,
    onEditEmailClick: () -> Unit,
    onEditPasswordClick: () -> Unit,
    onEditPersonalInfoClick: () -> Unit,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: ProfileInfoViewModel = koinViewModel()
    val profileInfoState by remember { viewModel.profileInfoState }
    Crossfade(targetState = profileInfoState, label = "") { state ->
        when(state) {
            is ProfileInfoState.Content ->
                Box(modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(ProfilePhotoBackgroundSize)
                            .background(PrimaryColorLight)
                    )
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(PaddingMedium),
                        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Image(
                                painter = painterResource(id = photoId),
                                contentDescription = stringResource(id = R.string.profilePhoto),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(top = PaddingMedium)
                                    .size(ProfilePhotoSize)
                                    .clip(CircleShape)
                                    .align(Alignment.BottomCenter)
                                    .border(
                                        ProfilePhotoBorderSize,
                                        MaterialTheme.colorScheme.background,
                                        CircleShape
                                    )
                            )
                        }
                        ProfileInfoBody(
                            email = state.user.email,
                            name = state.user.name,
                            surname = state.user.surname,
                            patronymic = state.user.patronymic,
                            onEditEmailClick = onEditEmailClick,
                            onEditPasswordClick = onEditPasswordClick,
                            onEditPersonalInfoClick = onEditPersonalInfoClick,
                            modifier = modifier
                        )
                    }
                }
            is ProfileInfoState.Error -> ErrorScreen(messageId = state.messageId, onRetryClick = viewModel::getProfile)
            ProfileInfoState.Loading -> LoadingProgress()
            ProfileInfoState.SignedOut -> LaunchedEffect(Unit) {
                onSignOut()
            }
        }
    }
}

@Composable
fun ProfileInfoBody(
    email: String,
    name: String,
    surname: String,
    patronymic: String,
    onEditEmailClick: () -> Unit,
    onEditPasswordClick: () -> Unit,
    onEditPersonalInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "$name $surname",
            color = MaterialTheme.colorScheme.onBackground,
            style = TopAppBarStyle,
            textAlign = TextAlign.Center
        )
        ProfileInfoItems(
            email = email,
            name = name,
            surname = surname,
            patronymic = patronymic,
            onEditEmailClick = onEditEmailClick,
            onEditPasswordClick = onEditPasswordClick,
            onEditPersonalInfoClick = onEditPersonalInfoClick
        )
    }
}

@Composable
fun ProfileInfoItems(
    email: String,
    name: String,
    surname: String,
    patronymic: String,
    onEditEmailClick: () -> Unit,
    onEditPasswordClick: () -> Unit,
    onEditPersonalInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(vertical = PaddingLarge),
        verticalArrangement = Arrangement.spacedBy(PaddingMedium)
    ) {
        ProfileInfoItem(
            label = stringResource(id = R.string.email),
            fieldValue = email,
            onEditClick = onEditEmailClick
        )
        ProfileInfoItem(
            label = stringResource(id = R.string.password),
            fieldValue = null,
            onEditClick = onEditPasswordClick
        )
        ProfileInfoItem(
            label = stringResource(id = R.string.name),
            fieldValue = name,
            onEditClick = onEditPersonalInfoClick
        )
        ProfileInfoItem(
            label = stringResource(id = R.string.surname),
            fieldValue = surname,
            onEditClick = onEditPersonalInfoClick
        )
        ProfileInfoItem(
            label = stringResource(id = R.string.patronymic),
            fieldValue = patronymic,
            onEditClick = onEditPersonalInfoClick
        )
    }
}