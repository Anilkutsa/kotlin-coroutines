package com.kotlincoroutines.demo.retrofit_example

import com.kotlincoroutines.demo.retrofit_example.retrofit.Album
import com.kotlincoroutines.demo.retrofit_example.retrofit.AlbumService
import com.kotlincoroutines.demo.retrofit_example.retrofit.RetrofitInstance

class RetrofitDemoRepository {

    var albumService: AlbumService = RetrofitInstance.albumService

    suspend fun getAlbum(albumId: Int): Album = albumService.getAlbum(albumId)


}