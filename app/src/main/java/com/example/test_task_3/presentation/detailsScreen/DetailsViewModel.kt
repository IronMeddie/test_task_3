package com.example.test_task_3.presentation.detailsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_3.data.remote.DataResource
import com.example.test_task_3.domain.models.MyItem
import com.example.test_task_3.domain.use_cases.GetDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel
@Inject constructor(savedStateHandle: SavedStateHandle, private val useCase: GetDetails) :
    ViewModel() {

    private val _item = MutableStateFlow<DataResource<MyItem>>(DataResource.Loading)
    val item = _item.asStateFlow()

    private var id = 0

    init {
        id = savedStateHandle.get<Int>("id") ?: 0
        getItem(id)
    }

    fun getItem(id: Int) {
        useCase(id).onEach {
            _item.value = it
        }.launchIn(viewModelScope)
    }
}