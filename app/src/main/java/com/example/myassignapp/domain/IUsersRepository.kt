package com.example.myassignapp.domain

import com.example.myassignapp.data.models.rooom_models.UserTable
import kotlinx.coroutines.flow.Flow

/**
 * Created by Madhuri Patil on 3/14/2024.
 */
interface IUsersRepository {
    suspend fun refreshUsers()
    val allUsers: Flow<List<UserTable>>
}