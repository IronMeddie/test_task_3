package com.example.test_task_3.domain.use_cases

import com.example.test_task_3.domain.constance.Constance
import com.example.test_task_3.domain.repository.Repository
import javax.inject.Inject

class GetMainList @Inject constructor(private val repository: Repository) {

    operator fun invoke(page: Int, query:String) = repository.getData(page * Constance.PAGE_SIZE, query)
}