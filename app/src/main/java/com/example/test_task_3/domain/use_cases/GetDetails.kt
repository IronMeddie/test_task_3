package com.example.test_task_3.domain.use_cases

import com.example.test_task_3.domain.repository.Repository
import javax.inject.Inject

class GetDetails @Inject constructor(private val repository: Repository) {
    operator fun invoke(id: Int) = repository.getItem(id)
}