package com.example.crowdfundingplatform.data.model

import com.google.gson.annotations.SerializedName

enum class PersonRole {
    @SerializedName("ROLE_USER") ROLE_USER,
    @SerializedName("ROLE_MODER") ROLE_MODER,
    @SerializedName("ROLE_ADMIN") ROLE_ADMIN
}