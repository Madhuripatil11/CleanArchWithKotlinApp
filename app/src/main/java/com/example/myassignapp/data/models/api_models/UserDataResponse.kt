package com.example.myassignapp.data.models.api_models

import com.google.gson.annotations.SerializedName

/**
 * Created by Madhuri Patil on 3/14/2024.
 */
data class UserDataResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("per_page") var perPage: Int? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("data") var data: ArrayList<User> = arrayListOf(),
    @SerializedName("support") var support: Support? = Support()
)

data class User(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("avatar") var avatar: String? = null
)

data class Support(
    @SerializedName("url") var url: String? = null,
    @SerializedName("text") var text: String? = null
)