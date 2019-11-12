package com.kotlincoroutines.demo.concepts_demo

import kotlinx.coroutines.*

fun main(args: Array<String>) {

    GlobalScope.launch {
        println("GlobalScope current thread is ${Thread.currentThread().name}")
    }
    CoroutineScope(Dispatchers.Main).launch {
        println("CoroutineScope/Dispatchers.Main - Current Thread is ${Thread.currentThread().name}")
    }
    CoroutineScope(Dispatchers.IO).launch {
        println("CoroutineScope/Dispatchers.IO - Current Thread is ${Thread.currentThread().name}")
    }
    CoroutineScope(Dispatchers.Default).launch {
        println("CoroutineScope/Dispatchers.Default - Current Thread is ${Thread.currentThread().name}")
    }
    CoroutineScope(Dispatchers.Unconfined).launch {
        println("CoroutineScope/Dispatchers.Unconfined - Current Thread is ${Thread.currentThread().name}")
    }

    // Unconfined vs Confined
    UnconfinedVsConfined()
}

fun UnconfinedVsConfined() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    launch { // context of the parent, main runBlocking coroutine
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}

