package com.yourcompany.n11casestudy.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yourcompany.n11casestudy.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    var search by mutableStateOf("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = snapshotFlow { search }.flatMapLatest { query ->
        delay(1000)
        if (query.isNotEmpty()) {
            repository.getUsersByPage(query)
        } else {
            flowOf(PagingData.empty())
        }
    }.cachedIn(viewModelScope)
    fun setSearchName(newName: String) {
        search = newName
    }
}