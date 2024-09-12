package com.yourcompany.n11casestudy.screens.profile

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
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    var newProfileName by mutableStateOf<String?>("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<ProfileUiState> =
        snapshotFlow { newProfileName }.flatMapLatest { query ->
            flow {
                if (!query.isNullOrEmpty()) {
                    emit(ProfileUiState.Loading)
                    delay(1000)
                    val usersDetail = repository.getUsersDetail(query)
                    emit(ProfileUiState.Success(usersDetail))
                } else {
                    emit(ProfileUiState.Init)
                }
            }
        }.catch {
            emit(ProfileUiState.Error)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProfileUiState.Init
        )

    fun setProfileName(newName: String?) {
        newProfileName = newName
    }
}
