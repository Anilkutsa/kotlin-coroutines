# kotlin-coroutines
Basic kotlin coroutine concepts. 

Important Links - 
- **[Coroutine Cores]**(https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/index.html)
- **[Coroutine Packages]**(https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/index.html)
- **[Coroutine Basics]**(https://kotlinlang.org/docs/reference/coroutines/basics.html)

### Important Types (Basics)
The basic building block of coroutine looks like this -
```
CoroutineScope(Dispatchers.IO).launch {
    // task to do in background...
}
```
We can break above statement into 3 parts - Scopes, Context and Builders. 

##### [Scopes](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/)
- **CoroutineScope**
- **GlobalScope** - meaning that the lifetime of the new coroutine is limited only by the lifetime of the whole application.
- **ProducerScope**
- **ActorScope**

##### [Context](https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html) 
The coroutine context includes a coroutine dispatcher that determines what thread or threads the corresponding coroutine uses for its execution.
- **Dispatchers.Main** - Coroutines will run in the main/UI thread. You should use this to call UI function, get updates from Livedata or doing small tasks.
- **Dispatchers.IO** - Coroutines will run in background thread from a shared pool of on-demand threads. You can use this network calls, db updates etc
- **Dispatchers.Default** - We should use this incase of CPU intensive tasks like sorting a huge list etc.
- **Dispatchers.Unconfined** - Coroutines will run in current thread, but if its suspended and resumed, it will run on suspending function's thread

##### [Builders](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/index.html)
They are extension functions of coroutines scopes, which are used for launching new coroutine.
- **launch** - Launches a new coroutine without blocking current thread. Launches coroutine that does not have any result. Returns job result which can used for reference but does not return any computational result.
- **async** - Helps launch coroutines in parallel without blocking the current thread. Returns an instance of Deferred<T>. We need to call await() to get the value. We can use this for couroutines that want to pass result as return value.
- **produce** - Produces a stream of elements of result type ReceiveChannel.
- **runBlocking** - The coroutine we build using this builder/thread will block the thread while coroutine is executing. Returns a result of type T which we can directly use.
  
##### [Coroutine Start](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-start/index.html)
Sometimes, we may want coroutines to run after certain condition has been satified. In such cases, we can use this. 

### Important Functions
- **Delay** Delay suspends coroutines for given period of time without blocking the current thread.
- **Async and Await** All the code written inside coroutines is synchronous. If we want then them to be asynchronous, then we have to use async. If async takes times in replying back, we use await to fetch results. See RetrofitDemoActivity.kt for implementation.
- **withTimeout** Runs a specific block of code for given time inside a coroutine scope
- **withTimeoutorNull** 
- **runBlocking** This blocks UI thread till the code block inside has completed execution
- **[Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html)** A background job. Conceptually, a job is a cancellable thing with a life-cycle that culminates in its completion. See JobCoroutine for more details.
- **[Deferred](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/index.html)** Deferred value is a non-blocking cancellable future — it is a Job with a result.
- **Join** job.join ensures that the coroutine block is executed before executing code after the block. Check MainActivity for more details.

### Concurrency 
Its is when you want to run multiple tasks concurrently. There are 2 ways we can achieve this -
- **UnStructured Concurrency** - It does not guarantee to complete all the tasks of the suspending function, before it returns. We can prevent this problem by using async with await.
See Concurrency class for more details
- **Structured Concurrency** - Above problem can be fixed using coroutuneScope (Suspending Function). coroutuneScope gurantees completion of all child tasks before return of the function. Structured concurrency also us to keep track of tasks we started and to cancel them when needed. See Concurrency class for more details
