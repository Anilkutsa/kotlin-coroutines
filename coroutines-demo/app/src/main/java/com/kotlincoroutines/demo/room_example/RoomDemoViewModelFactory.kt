package com.kotlincoroutines.demo.room_example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RoomDemoViewModelFactory(private val repository : RoomDemoRepository) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDemoViewModel::class.java)) {
            return RoomDemoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
