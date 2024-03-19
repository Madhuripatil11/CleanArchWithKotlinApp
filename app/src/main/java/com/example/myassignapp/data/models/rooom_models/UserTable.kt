package com.example.myassignapp.data.models.rooom_models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myassignapp.data.models.rooom_models.TableNames.USERS_TABLE

/**
 * Created by Madhuri Patil on 3/14/2024.
 */
@Entity(tableName = USERS_TABLE)
data class UserTable(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "avatar") val avatar: String
)