package com.example.test.ui.data.dto.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("short_token") var short_token: String,
    @SerializedName("error_code") var error_code: String,
    @SerializedName("error_description") var error_description: String,
)
