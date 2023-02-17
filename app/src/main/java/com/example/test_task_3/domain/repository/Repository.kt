package com.example.test_task_3.domain.repository

import com.example.test_task_3.data.remote.DataResource
import com.example.test_task_3.domain.models.MyItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getData(offSet: Int, query: String) : Flow<DataResource<List<MyItem>>>
    fun getItem(id: Int) : Flow<DataResource<MyItem>>

}