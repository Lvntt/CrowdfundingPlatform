package com.example.crowdfundingplatform.presentation.ui.screen.creation

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.crowdfundingplatform.R
import com.example.crowdfundingplatform.presentation.ui.theme.LabelBoldStyleAlternative
import com.example.crowdfundingplatform.presentation.ui.theme.LabelRegularStyle
import com.example.crowdfundingplatform.presentation.ui.theme.OnTertiaryContainerLight
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingLarge
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingMedium
import com.example.crowdfundingplatform.presentation.ui.theme.PaddingSmall
import com.example.crowdfundingplatform.presentation.ui.theme.RoundedCornerShapePercentLarge
import com.example.crowdfundingplatform.presentation.ui.theme.Subtitle
import com.example.crowdfundingplatform.presentation.ui.theme.TertiaryContainerLight
import com.example.crowdfundingplatform.presentation.ui.theme.TextButtonIconSizeMedium
import com.example.crowdfundingplatform.presentation.ui.theme.UploadProjectAvatarSize
import com.example.crowdfundingplatform.presentation.uistate.creation.ImageUploadState
import com.example.crowdfundingplatform.presentation.viewmodel.ProjectCreationViewModel

@Composable
fun AvatarBody(
    projectCreationViewModel: ProjectCreationViewModel,
    modifier: Modifier = Modifier
) {
    val imageUploadState by remember { projectCreationViewModel.imageUploadState }

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            imageUri = uri
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

        Button(
            modifier = modifier,
            onClick = {
                      launcher.launch("image/*")
            },
            contentPadding = PaddingValues(start = PaddingSmall, end = PaddingMedium),
            shape = RoundedCornerShape(RoundedCornerShapePercentLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = TertiaryContainerLight,
                contentColor = OnTertiaryContainerLight
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.file_upload),
                contentDescription = null,
                modifier = Modifier.size(TextButtonIconSizeMedium)
            )
            Text(
                text = stringResource(id = R.string.upload),
                style = LabelBoldStyleAlternative
            )
        }

        when (imageUploadState) {
            ImageUploadState.Initial -> Unit
            ImageUploadState.Success -> Unit
            ImageUploadState.Error -> Text(
                text = stringResource(id = R.string.unableToGetImage),
                style = LabelRegularStyle,
                color = Color.Red
            )
        }

        imageUri?.let { uri ->
            bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }

            bitmap?.asImageBitmap()?.let { bitmap ->
                Image(
                    bitmap = bitmap,
                    contentDescription = null,
                    modifier = Modifier.size(UploadProjectAvatarSize)
                )
            }

            projectCreationViewModel.setProjectAvatarId(uri)
        }

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