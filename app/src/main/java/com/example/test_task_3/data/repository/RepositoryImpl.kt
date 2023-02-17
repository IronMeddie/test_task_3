package com.example.test_task_3.data.repository

import com.example.test_task_3.data.remote.ApiService
import com.example.test_task_3.data.remote.DataResource
import com.example.test_task_3.domain.models.MyItem
import com.example.test_task_3.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository, BaseRepository{

    override fun getData(offSet: Int, query: String): Flow<DataResource<List<MyItem>>> = safeApiCall { apiService.getData(offSet = offSet, search = query.ifEmpty { null }) }
    override fun getItem(id: Int): Flow<DataResource<MyItem>> = safeApiCall { apiService.getItem(id) }
}