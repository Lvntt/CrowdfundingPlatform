package com.example.crowdfundingplatform.domain.usecase

import android.net.Uri
import com.example.crowdfundingplatform.domain.repository.FileRepository

class UploadFileAndGetIdUseCase(
    private val fileRepository: FileRepository
) {

    suspend operator fun invoke(fileUri: Uri): String = fileRepository.uploadFileAndGetId(fileUri)

}