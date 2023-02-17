package com.example.test_task_3.data.remote

import okhttp3.ResponseBody

sealed class DataResource<out T> {
    data class Success<out T>(val data: T) : DataResource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val otherMessage: String?
    ) : DataResource<Nothing>()

    object Loading : DataResource<Nothing>()
}