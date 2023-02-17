package com.example.test_task_3.data.repository

import com.example.test_task_3.data.remote.DataResource
import com.example.test_task_3.domain.constance.Constance.NO_INTERNET
import com.example.test_task_3.domain.constance.Constance.OTHER_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface BaseRepository {
     fun <T> safeApiCall(apiCall: suspend () -> T): Flow<DataResource<T>> {
        return flow {
            val resp = withContext(Dispatchers.IO) {
                try {
                     DataResource.Success(apiCall.invoke())
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is HttpException -> {
                             DataResource.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.message
                            )

                        }
                        else -> {
                            if (throwable.message == NO_INTERNET) {
                                 DataResource.Failure(true, null, null, throwable.message)

                            } else {
                                DataResource.Failure(true, OTHER_ERROR, null, throwable.message)
                            }
                        }
                    }
                }
            }
            emit(resp)
        }
    }
}