package com.example.myassignapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myassignapp.data.models.rooom_models.UserTable
import kotlinx.coroutines.flow.Flow

/**
 * Created by Madhuri Patil on 3/14/2024.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM USERS_TABLE")
    fun getAllUsers(): Flow<List<UserTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserTable>)
}