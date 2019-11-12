package com.kotlincoroutines.demo.retrofit_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlincoroutines.demo.R
import kotlinx.android.synthetic.main.activity_job_coroutine.*

class RetrofitDemoActivity : AppCompatActivity() {
    lateinit var viewModel: RetrofitDemoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel)
        viewModel = ViewModelProvider(this).get(RetrofitDemoViewModel::class.java)
        getAlbum()
    }

    private fun getAlbum() {
        viewModel.album.observe(this, Observer {
            textView.text = it.title
        })
    }

}
