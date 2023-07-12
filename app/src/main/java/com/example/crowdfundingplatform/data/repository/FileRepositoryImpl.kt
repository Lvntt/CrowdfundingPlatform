package com.example.crowdfundingplatform.data.repository

import android.content.ContentResolver
import android.net.Uri
import com.example.crowdfundingplatform.data.remote.api.FileApiService
import com.example.crowdfundingplatform.domain.repository.FileRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class FileRepositoryImpl(
    private val fileApiService: FileApiService,
    private val contentResolver: ContentResolver
) : FileRepository {

    override suspend fun uploadFileAndGetId(fileUri: Uri): String {
        val fileStream = contentResolver.openInputStream(fileUri)
        val fileBytes = fileStream?.readBytes()
        fileStream?.close()
        val multipartBody = MultipartBody.Part.createFormData("file", fileUri.lastPathSegment, fileBytes!!.toRequestBody())
        return fileApiService.uploadFile(multipartBody).id
    }

}