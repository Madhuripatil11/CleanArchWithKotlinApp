package com.example.myassignapp.data.api

import com.example.myassignapp.data.models.api_models.RefreshTokenRequest
import com.example.myassignapp.data.models.api_models.RefreshTokenResponse
import com.example.myassignapp.data.models.api_models.UserDataResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Madhuri Patil on 3/14/2024.
 */
interface ApiService {
    @GET("users?page=2")
    suspend fun getAllUsers(): Response<UserDataResponse>

    @GET("users?page=2")
    fun getUsers() : Call<UserDataResponse>

    @POST("api/auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<RefreshTokenResponse>
}