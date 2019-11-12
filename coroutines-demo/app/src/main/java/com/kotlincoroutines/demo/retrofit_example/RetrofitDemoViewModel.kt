package com.kotlincoroutines.demo.retrofit_example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class RetrofitDemoViewModel : ViewModel(){
    val albumsRepository = RetrofitDemoRepository()
    val album = liveData {
        val receivedAlbum = albumsRepository.getAlbum(5)
        emit(receivedAlbum)
    }
}