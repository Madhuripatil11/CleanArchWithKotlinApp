package com.example.myassignapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myassignapp.data.models.rooom_models.UserTable

/**
 * Created by Madhuri Patil on 3/14/2024.
 */
@Database(
    entities = [UserTable::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}