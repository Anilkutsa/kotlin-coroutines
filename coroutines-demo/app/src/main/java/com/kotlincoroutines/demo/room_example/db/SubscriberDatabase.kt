package com.kotlincoroutines.demo.room_example.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1, exportSchema = false)
abstract class SubscriberDatabase : RoomDatabase() {
    abstract val subscriberDAO: SubscriberDAO

    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null

        fun getInstance(context: Context): SubscriberDatabase {
           synchronized(this){
             var instance = INSTANCE
               if(instance==null) {
                   instance = Room.databaseBuilder(
                       context.applicationContext,
                       SubscriberDatabase::class.java,
                       "subscriber_data_database"
                   ).fallbackToDestructiveMigration()
                       .build()
               }
              return instance
           }
            
        }


    }
}