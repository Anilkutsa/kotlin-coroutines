package com.kotlincoroutines.demo.viewmodels_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlincoroutines.demo.R

class VMDemoActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: VMDemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel)

        mainActivityViewModel = ViewModelProvider(this).get(VMDemoViewModel::class.java)

        // mainActivityViewModel.getUsers()
        mainActivityViewModel.users.observe(this, Observer { myUsers ->
            myUsers.forEach {
                Log.i("MyTag", "name is ${it.name}")
            }

        })

    }
}
