package com.kotlincoroutines.demo.room_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlincoroutines.demo.R
import com.kotlincoroutines.demo.room_example.db.Subscriber
import com.kotlincoroutines.demo.room_example.db.SubscriberDatabase
import kotlinx.android.synthetic.main.activity_room.*

class RoomDemoActivity : AppCompatActivity() {
    private lateinit var subscriberViewModel: RoomDemoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = RoomDemoRepository(dao)
        val viewModelFactory = RoomDemoViewModelFactory(repository)
        subscriberViewModel =
            ViewModelProvider(this, viewModelFactory).get(RoomDemoViewModel::class.java)

        displaySubscribersList()

        save_button.setOnClickListener {
            val subscriber = Subscriber(0, name_text.text.toString(), email_text.text.toString())
            subscriberViewModel.insert(subscriber)
        }

        clear_button.setOnClickListener {
            subscriberViewModel.clearAll()
        }
    }

    private fun displaySubscribersList() {
        subscriberViewModel.subscribers.observe(this, Observer {
            subscribers_textview.text = it.toString()
        })
    }

}
