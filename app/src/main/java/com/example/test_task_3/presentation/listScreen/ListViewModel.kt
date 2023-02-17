package com.example.test_task_3.presentation.listScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_3.data.remote.DataResource
import com.example.test_task_3.domain.constance.Constance
import com.example.test_task_3.domain.models.MyItem
import com.example.test_task_3.domain.use_cases.GetMainList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val usecase: GetMainList) : ViewModel() {


    private val _state = MutableStateFlow(ScreenState())
    val state = _state.asStateFlow()

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()


    init {
        loadNextItems()
    }

    fun loadNextItems() {
        _state.value = _state.value.copy(isLoading = true, page = state.value.page+1)
        usecase(state.value.page, search.value).onEach {
            when (it) {
                is DataResource.Success -> {
                    _state.value =
                        state.value.copy(
                            items = state.value.items + it.data,
                            isLoading = false,
                            endReached = it.data.size < Constance.PAGE_SIZE
                        )
                    Log.d("checkData", "loadsize = " + it.data.size.toString())
                }
                is DataResource.Loading -> Unit
                is DataResource.Failure -> _state.value =
                    _state.value.copy(error = it.errorBody.toString(), isLoading = false)
            }
        }.launchIn(viewModelScope)
    }

    private var job: Job? = null

    fun onSearch(string: String){
        if (!string.contains("\n")) _search.value = string
        job?.cancel()
        job = viewModelScope.launch {
            delay(700)
            _state.value = _state.value.copy(items = emptyList(), page = -1, endReached = false)
            loadNextItems()
        }
    }


}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<MyItem> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = -1
)



