package com.kotlincoroutines.demo.init;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlincoroutines.demo.R
import kotlinx.android.synthetic.main.activity_concurrency.*
import kotlinx.coroutines.*

class ConcurrencyActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concurrency)

        btnDownloadUserData.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                //tvUserMessage.text = UnstructuredDataManager().getTotalUserCount().toString()
                tvUserMessage.text = StructuredDataManager().getTotalUserCount().toString()
            }
        }
    }
}
