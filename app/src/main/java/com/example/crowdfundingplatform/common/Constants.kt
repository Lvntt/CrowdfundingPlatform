package com.example.crowdfundingplatform.common

object Constants {
    const val EMPTY_STRING = ""
    const val BASE_URL = "http://kosterror.ru:8081/api/v1/"
    const val FILE_URL = "files/"
    const val UPLOAD_FILE_URL = "files"
    const val REGISTER_URL = "auth/register"
    const val LOGIN_URL = "auth/login"
    const val REFRESH_URL = "auth/tokens"
    const val LOGOUT_URL = "auth/logout"
    const val CREATE_PROJECT_URL = "projects"
    const val PROJECTS_SEARCH_URL = "projects/search"
    const val FULL_PROJECT_INFO_URL = "projects/{projectId}"
    const val FUND_PROJECT_URL = "projects/{projectId}/sponsorship"
    const val YOUR_PROFILE_URL = "profiles"
    const val PROMOCODE_URL = "promo-codes"
    const val TOKEN_PREFERENCES = "token_preferences"
    const val USER_ACCESS_TOKEN = "access_token"
    const val USER_REFRESH_TOKEN = "refresh_token"
    const val DEFAULT_PAGE_SIZE = 5
    const val DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val EXPIRES_AFTER_MONTHS = 36
}