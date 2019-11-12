package com.kotlincoroutines.demo.room_example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlincoroutines.demo.room_example.db.Subscriber
import kotlinx.coroutines.launch

class RoomDemoViewModel(private val repository : RoomDemoRepository) : ViewModel() {

    val subscribers = repository.subscribers

    fun insert(subscriber: Subscriber) = viewModelScope.launch {
        repository.insert(subscriber)
    }

    fun clearAll(){
        viewModelScope.launch {
            repository.clearAll()
        }
    }


}