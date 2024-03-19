package com.example.myassignapp.data.models.api_models

import com.google.gson.annotations.SerializedName

/**
 * Created by Madhuri Patil on 3/14/2024.
 */
data class RefreshTokenRequest(val refreshToken: String)

data class RefreshTokenResponse(@SerializedName("data") var data: RefreshTokenData? = RefreshTokenData())

data class RefreshTokenData(
    @SerializedName("userId") var userId: String? = null,
    @SerializedName("accessToken") var accessToken: String? = null,
    @SerializedName("refreshToken") var refreshToken: String? = null
)