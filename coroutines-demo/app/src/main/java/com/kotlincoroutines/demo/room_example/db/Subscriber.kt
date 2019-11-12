package com.kotlincoroutines.demo.room_example.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_data_table")
data class Subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_id")
    val subscriberId : Int,

    @ColumnInfo(name = "subscriber_name")
    var name : String,

    @ColumnInfo(name = "subscriber_email")
    var email : String

) {
    override fun toString(): String {
        return "\n name='$name',\n email='$email'\n"
    }
}

