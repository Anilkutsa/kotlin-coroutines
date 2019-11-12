package com.kotlincoroutines.demo.viewmodels_example

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers

class VMDemoViewModel : ViewModel() {

    private var usersRepository = VMDemoRepository()

    var users = liveData(Dispatchers.IO) {
        val result = usersRepository.getUsers()
        emit(result)
    }


// var users: MutableLiveData<List<User>> = MutableLiveData()

//    fun getUsers() {
//        viewModelScope.launch {
//            var result: List<User>? = null
//            withContext(Dispatchers.IO) {
//                result = usersRepository.getUsers()
//            }
//            users.value = result
//        }
//    }
}