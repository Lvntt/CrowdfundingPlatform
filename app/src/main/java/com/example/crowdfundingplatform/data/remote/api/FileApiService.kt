package com.example.crowdfundingplatform.data.remote.api

import com.example.crowdfundingplatform.common.Constants
import com.example.crowdfundingplatform.data.remote.model.FileUploadResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileApiService {

    @Multipart
    @POST(Constants.UPLOAD_FILE_URL)
    suspend fun uploadFile(@Part filePart: MultipartBody.Part): FileUploadResponse

}