package com.example.crowdfundingplatform.presentation.ui.screen.creation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.common.UploadButton
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyle
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingLarge
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.ui.theme.UploadProjectAvatarSize
import com.example.crowdfundingplatform.presentation.uistate.creation.ImageUploadState
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectCreationViewModel

@Composable
fun AvatarContent(
    projectCreationViewModel: ProjectCreationViewModel,
    modifier: Modifier = Modifier
) {
    val imageUploadState by remember { projectCreationViewModel.imageUploadState }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            imageUri = uri
            if (uri != null) {
                projectCreationViewModel.setProjectAvatarId(uri)
            }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(PaddingMedium)
            .verticalScroll(rememberScrollState())
    ) {
        val stepText = stringResource(id = R.string.step2of4)
        val desc = stringResource(id = R.string.avatar)

        Text(
            text = "$stepText $desc",
            style = Subtitle
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        Text(
            text = stringResource(id = R.string.avatarHint),
            style = LabelRegularStyle
        )

        Spacer(modifier = Modifier.height(PaddingSmall))

        UploadButton(modifier = modifier, onClick = {
            launcher.launch("image/*")
        })

        when (imageUploadState) {
            ImageUploadState.Initial -> Unit
            ImageUploadState.Success -> Unit
            ImageUploadState.Error -> Text(
                text = stringResource(id = R.string.unableToGetImage),
                style = LabelRegularStyle,
                color = Color.Red
            )
        }

        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = null,
            modifier = Modifier.size(UploadProjectAvatarSize)
        )

        Spacer(modifier = Modifier.height(PaddingLarge))

        NextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                projectCreationViewModel.openCategory()
                imageUri = null
            }
        )
    }
}