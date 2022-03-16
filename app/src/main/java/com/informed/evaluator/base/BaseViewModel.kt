package com.informed.evaluator.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*


open class BaseViewModel : ViewModel() {

    val job = SupervisorJob()
    val mainScope = CoroutineScope(Dispatchers.Main + job)

    val handlerException= CoroutineExceptionHandler { _, exception ->
        println("Exception Received : $exception")
    }

    override fun onCleared() {
mainScope.cancel()
    }


}