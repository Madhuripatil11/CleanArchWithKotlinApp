package com.example.myassignapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myassignapp.data.api.ApiService
import com.example.myassignapp.data.db.UserDao
import com.example.myassignapp.data.models.api_models.User
import com.example.myassignapp.data.models.api_models.UserDataResponse
import com.example.myassignapp.data.models.rooom_models.UserTable
import com.example.myassignapp.domain.IUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Madhuri Patil on 3/14/2024.
 */
class UsersRepository @Inject constructor(
    private val userDao: UserDao,
    val apiService: ApiService
) :
    IUsersRepository {

    /* override suspend fun getUsersFromServer(): ResultWrapper<Response<UserDataResponse>> =
        safeApiCall(Dispatchers.IO) {
            apiService.getAllUsers()
        } */

    override val allUsers: Flow<List<UserTable>> = userDao.getAllUsers()

    var userList = ArrayList<User>()
    override suspend fun refreshUsers() {
        try {
            val response = apiService.getAllUsers()
            if (response.isSuccessful) {
                if (response.code() == 200) {
                    Log.e("userRespo", "refreshUsers: " + response.body().toString())
                    userList = response.body()!!.data
                    val users = response.body()?.data?.map { user ->
                        UserTable(
                            id = user.id!!,
                            email = user.email!!,
                            firstName = user.firstName!!,
                            lastName = user.lastName!!,
                            avatar = user.avatar!!
                        )
                    }
                    if (users != null) {
                        userDao.insertAll(users)
                    } // Insert the mapped data into the database
                }
            }

        } catch (e: Exception) {
            Log.e("USER_API", e.message.toString())
        }
    }
}