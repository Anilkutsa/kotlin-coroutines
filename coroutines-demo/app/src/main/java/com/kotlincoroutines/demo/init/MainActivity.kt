package com.kotlincoroutines.demo.init

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlincoroutines.demo.R
import com.kotlincoroutines.demo.retrofit_example.RetrofitDemoActivity
import com.kotlincoroutines.demo.room_example.RoomDemoActivity
import com.kotlincoroutines.demo.viewmodels_example.VMDemoActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = "RetrofitDemoActivity"
    }

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(TAG, "************* START OF ONCREATE *************")

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }

        btnDownloadUserData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }

        btnJobCoroutine.setOnClickListener {
            startActivity(Intent(this, JobCoroutineActivity::class.java))
        }

        btnDeferredCoroutine.setOnClickListener {
            startActivity(Intent(this, DeferredCoroutineActivity::class.java))
        }

        btnConcurrency.setOnClickListener {
            startActivity(Intent(this, ConcurrencyActivity::class.java))
        }

        btnViewModelExample.setOnClickListener {
            startActivity(Intent(this, VMDemoActivity::class.java))
        }

        btnRetrofitExample.setOnClickListener {
            startActivity(Intent(this, RetrofitDemoActivity::class.java))
        }

        btnRoomExample.setOnClickListener {
            startActivity(Intent(this, RoomDemoActivity::class.java))
        }

//        // Synchronous execution of processes
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.i(TAG, "Calculation started....")
//            val result1 = getResults1()
//            val result2 = getResults2()
//            Log.i(TAG,"Total is ${result1 + result2}")
//        }

//        // Asynchronous execution of processes
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.i(TAG,"Calculation started....")
//            val result1 = async { getResults1() }
//            val result2 = async { getResults2() }
//            Log.i(TAG,"Total is ${result1.await() + result2.await()}")
//        }

//        // Here coroutine runs on main thread but methods are executed in IO thread
//        CoroutineScope(Dispatchers.Main).launch {
//            Log.i(TAG,"Calculation started....")
//            val deferredResult1 = async(Dispatchers.IO) { getResults1() }
//            val deferredResult2 = async(Dispatchers.IO) { getResults2() }
//            Log.i(TAG,"Total is ${deferredResult1.await() + deferredResult2.await()}")
//        }

//        // LAZY ensures that async methods get called only after await has been called.
//        CoroutineScope(Dispatchers.Main).launch {
//            Log.i(TAG,"Calculation started....")
//            val deferredResult1 = async(start = CoroutineStart.LAZY) { getResults1() }
//            val deferredResult2 = async(start = CoroutineStart.LAZY) { getResults2() }
//
//            delay(2000)
//            Log.i(TAG, "************ AWAIT GETTING CALLED **************")
//            Log.i(TAG,"Total is ${deferredResult1.await() + deferredResult2.await()}")
//        }

//        // withTimeout stops coroutine task after the defined time period
//        CoroutineScope(Dispatchers.Main).launch {
//            withTimeout(5000) {
//                withContext(Dispatchers.IO){
//                    someLongRunningTask()
//                }
//            }
//        }

//        //This blocks UI thread till the code block inside has completed execution
//        runBlocking(Dispatchers.IO) {
//            withTimeoutOrNull(5000) {
//                someLongRunningTask()
//            }
//        }

//        // job.join ensures that the coroutine block is executed before executing code after the block
//        CoroutineScope(Dispatchers.Main).launch {
//            someShortTask()
//        }

        Log.i(TAG, "************* END OF ONCREATE *************")
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
            delay(3000)
        }
    }

    private suspend fun getResults1(): Int {
        Log.i(TAG, "Started Task for Result 1....")
        delay(5000)
        Log.i(TAG, "Result1 has been fetched. Returning...")
        return 5000
    }

    private suspend fun getResults2(): Int {
        Log.i(TAG, "Started Task for Result 2....")
        delay(10000)
        Log.i(TAG, "Result2 has been fetched. Returning...")
        return 10000
    }

    private suspend fun someLongRunningTask() {
        for (i in 1..200000) {
            delay(1000)
            Log.i(TAG, "Round $i")
        }
    }

    private suspend fun someShortTask() {
        coroutineScope {
            Log.i(TAG, "Before Task. Running in ${Thread.currentThread().name}")
            var job = launch(Dispatchers.IO) {
                Log.i(TAG, "Before Delay. Running in ${Thread.currentThread().name}")
                delay(1000)
                Log.i(TAG, "After Delay. Running in ${Thread.currentThread().name}")
            }
            job.join()
            Log.i(TAG, "After Task. Running in ${Thread.currentThread().name}")
        }

    }

}
