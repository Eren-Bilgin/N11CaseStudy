package com.yourcompany.n11casestudy.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourcompany.n11casestudy.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    var search by mutableStateOf("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<HomeUiState> = snapshotFlow { search }
        .flatMapLatest { query ->
            flow {
                if (query.isNotEmpty()) {
                    emit(HomeUiState.Loading)
                    delay(2000)
                    val users = repository.getUsers(query)
                    emit(HomeUiState.Success(users.items.orEmpty()))
                } else {
                    emit(HomeUiState.Init)
                }
            }
        }.catch {
            HomeUiState.Error
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState.Init
        )

    fun setSearchName(newName: String) {
        search = newName
    }
}

