package com.sample.perform.app.data.api.base

sealed class Result<T> {

    companion object {
        fun <T> fromRetrofitResult(retrofitResult: retrofit2.adapter.rxjava2.Result<T>): Result<T> {
            return if (retrofitResult.isError || !retrofitResult.response().isSuccessful) {
                Failure(retrofitResult.error() ?: Throwable(retrofitResult.response()?.code().toString()))
            } else {
                Success(retrofitResult.response().body())
            }
        }
    }
}

class Success<T>(val result: T) : Result<T>()
class Failure<T>(val throwable: Throwable) : Result<T>()