package com.kotlincoroutines.demo.concepts_demo

import kotlinx.coroutines.*

fun main() {

    println("Current thread is ${Thread.currentThread().name}")

    // Synchronous execution of processes
    CoroutineScope(Dispatchers.Main).launch {
        println("Calculation started....")
        val result1 = getResults1()
        val result2 = getResults2()
        println("Total is ${result1 + result2}")
    }
    println("Here >>>>>>>>")
}

private suspend fun getResults1(): Int {
    delay(5000)
    println("Result1 has been fetched. Returning...")
    return 5000
}

private suspend fun getResults2(): Int {
    delay(10000)
    println("Result2 has been fetched. Returning...")
    return 10000
}