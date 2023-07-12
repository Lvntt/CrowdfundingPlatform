package com.example.crowdfundingplatform.domain.repository

import android.net.Uri

interface FileRepository {

    suspend fun uploadFileAndGetId(fileUri: Uri): String

}