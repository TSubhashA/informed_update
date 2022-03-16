package com.informed.trainee.data.model

sealed class ResultOf<out T> {
    data class Progress(val loading: Boolean = false) : ResultOf<Nothing>()
    data class Success<out R>(val value: R) : ResultOf<R>()
    data class Empty(val message: String?) : ResultOf<Nothing>()
    data class Failed<out R>(val value: R) : ResultOf<R>()
    data class Failure(
        val message: String? = null,
        val throwable: Throwable? = null
    ) : ResultOf<Nothing>()
}