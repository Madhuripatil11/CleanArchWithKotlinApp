package com.example.myassignapp.ui.main.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myassignapp.data.models.api_models.User
import com.example.myassignapp.data.models.api_models.UserDataResponse
import com.example.myassignapp.data.models.rooom_models.UserTable
import com.example.myassignapp.data.repository.UsersRepository
import com.example.myassignapp.di.NetworkModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

/**
 * Created by Madhuri Patil on 3/15/2024.
 */
class UserViewModel(private val usersRepository: UsersRepository) : ViewModel() {
    private var userLiveData = MutableLiveData<List<User>>()

    fun getAllUsers() {
        viewModelScope.launch {
            usersRepository.refreshUsers()
            userLiveData.value=usersRepository.userList
            usersRepository.allUsers.collect { userLiveData.value }
        }
    }

    fun observeUserLiveData(): LiveData<List<User>> {
        return userLiveData
    }
}

class UserViewModelFactory(private val usersRepository: UsersRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            UserViewModel(this.usersRepository) as T
        } else {
            throw IllegalArgumentException("View model not found")
        }
    }
}