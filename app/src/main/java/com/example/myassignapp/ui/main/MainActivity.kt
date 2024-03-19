package com.example.myassignapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myassignapp.data.db.AppDatabase
import com.example.myassignapp.data.db.UserDao
import com.example.myassignapp.data.repository.UsersRepository
import com.example.myassignapp.databinding.ActivityMainBinding
import com.example.myassignapp.di.NetworkModule
import com.example.myassignapp.ui.main.viewmodel.UserViewModel
import com.example.myassignapp.ui.main.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var customAdapter : CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            MyAssignAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "APP_DATABASE"
        ).build()
        val userDao=db.userDao()
        val appservice= NetworkModule.RetrofitInstance.api
        val usersRepository=UsersRepository(userDao,appservice )
        val userViewModelFactory= UserViewModelFactory(usersRepository)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
//        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel=ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)
        viewModel.getAllUsers()
        viewModel.observeUserLiveData().observe(this, Observer { userList ->
            customAdapter.setUserList(userList)
        })

    }

    private fun prepareRecyclerView() {
        customAdapter = CustomAdapter()
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = customAdapter
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

