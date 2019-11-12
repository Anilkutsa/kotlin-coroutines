package com.kotlincoroutines.demo.init;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlincoroutines.demo.R
import kotlinx.android.synthetic.main.activity_job_coroutine.*
import kotlinx.coroutines.*

class JobCoroutineActivity : AppCompatActivity() {

    lateinit var job1: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_coroutine)

        job1 = CoroutineScope(Dispatchers.Main).launch {
            downloadData()
        }

        statusButton.setOnClickListener {
            if (job1.isActive) {
                textView.text = "Active"
            } else if (job1.isCancelled) {
                textView.text = "Cancelled"
            } else if (job1.isCompleted) {
                textView.text = "Completed"
            }
        }

        cancelButton.setOnClickListener {
            job1.cancel()
        }
    }

    private suspend fun downloadData() {
        withContext(Dispatchers.IO) {
            repeat(5) {
                delay(1000)
                Log.i(MainActivity.TAG, "repeating $it")
            }
        }
    }
}
