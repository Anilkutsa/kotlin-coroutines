package com.kotlincoroutines.demo.init;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlincoroutines.demo.R
import kotlinx.android.synthetic.main.activity_job_coroutine.*
import kotlinx.coroutines.*

class DeferredCoroutineActivity : AppCompatActivity() {

    lateinit var deferred: Deferred<Int>
    var returnedValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_coroutine)

        CoroutineScope(Dispatchers.Main).launch {
            deferred = async(Dispatchers.IO) {
                downloadData()
            }
            returnedValue = deferred.await()
            Log.i(MainActivity.TAG, "RETURNED VALUE >>>>>" + returnedValue)
        }

        statusButton.setOnClickListener {
            if (deferred.isActive) {
                textView.text = "Active"
            } else if (deferred.isCancelled) {
                textView.text = "Cancelled"
            } else if (deferred.isCompleted) {
                textView.text = "Completed"
            }
        }

        cancelButton.setOnClickListener {
            deferred.cancel()
        }
    }

    private suspend fun downloadData(): Int {
        var i = 0
        withContext(Dispatchers.IO) {
            repeat(5) {
                delay(1000)
                i++
                Log.i(MainActivity.TAG, "repeating $it")
            }
        }
        return i
    }
}
