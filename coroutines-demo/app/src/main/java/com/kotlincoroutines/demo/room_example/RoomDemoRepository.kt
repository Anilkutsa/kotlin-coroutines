package com.kotlincoroutines.demo.room_example

import com.kotlincoroutines.demo.room_example.db.Subscriber
import com.kotlincoroutines.demo.room_example.db.SubscriberDAO

class RoomDemoRepository(private val dao: SubscriberDAO) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber){
        dao.insert(subscriber)
    }

    suspend fun clearAll(){
        dao.deleteAll()
    }
}